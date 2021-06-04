package akka_streams

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl._

final case class Author(handle: String)

final case class Hashtag(name: String)

final case class Tweet(author: Author, timestamp: Long, body: String) {
  def hashtags: Set[Hashtag] =
    body
      .split(" ")
      .collect {
        case t if t.startsWith("#") => Hashtag(t.replaceAll("[^#\\w]", ""))
      }
      .toSet
}

object TweetMain extends App {
  val akkaTag = Hashtag("#akka")

  val tweets: Source[Tweet, NotUsed] = Source(
    Tweet(Author("rolandkuhn"), System.currentTimeMillis, "#akka rocks!") ::
    Tweet(Author("patriknw"), System.currentTimeMillis, "#akka !") ::
    Tweet(Author("bantonsson"), System.currentTimeMillis, "#akka !") ::
    Tweet(Author("drewhk"), System.currentTimeMillis, "#akka !") ::
    Tweet(Author("ktosopl"), System.currentTimeMillis, "#akka on the rocks!") ::
    Tweet(Author("mmartynas"), System.currentTimeMillis, "wow #akka !") ::
    Tweet(Author("akkateam"), System.currentTimeMillis, "#akka rocks!") ::
    Tweet(Author("bananaman"), System.currentTimeMillis, "#bananas rock!") ::
    Tweet(Author("appleman"), System.currentTimeMillis, "#apples rock!") ::
    Tweet(Author("drama"), System.currentTimeMillis, "we compared #apples to #oranges!") ::
    Nil)

  implicit val system: ActorSystem = ActorSystem("reactive-tweets")

  var result = tweets
    .filterNot(_.hashtags.contains(akkaTag)) // Remove all tweets containing #akka hashtag
    /*
     Tweet(Author(bananaman),1621597911045,#bananas rock!)
     Tweet(Author(appleman),1621597911045,#apples rock!)
     Tweet(Author(drama),1621597911045,we compared #apples to #oranges!)
    */
    .map(_.hashtags) // Get all sets of hashtags ...
    /*
     Set(Hashtag(#bananas))
     Set(Hashtag(#apples))
     Set(Hashtag(#apples), Hashtag(#oranges))
    */
    .reduce(_ ++ _) // ... and reduce them to a single set, removing duplicates across all tweets
    /*
     Set(Hashtag(#bananas), Hashtag(#apples), Hashtag(#oranges))
    */
    .mapConcat(identity) // Flatten the set of hashtags to a stream of hashtags
    /*
     Hashtag(#bananas)
     Hashtag(#apples)
     Hashtag(#oranges)
    */
    .map(_.name.toUpperCase) // Convert all hashtags to upper case
    /*
     #BANANAS
     #APPLES
     #ORANGES
    */
    .runWith(Sink.foreach(println)) // Attach the Flow to a Sink that will finally print the hashtags

  implicit val ec = system.dispatcher
  result.onComplete{res =>
    system.terminate()
    }
}
