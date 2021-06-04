package akka_streams.graph

final case class Author(handle: String)
final case class HashTag(name: String)
final case class Tweet(author: Author, timestamp: Long, body:String) {
  def hashTags: Set[HashTag] =
    body
      .split(" ")
      .collect{
        case t if t.startsWith("#") => HashTag(t.replaceAll("[^#\\w]", ""))
      }
      .toSet
}

object MainGraph extends App {

}
