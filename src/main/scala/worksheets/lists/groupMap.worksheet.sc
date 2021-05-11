val events = List(("a", "1"), ("a", "2"), ("b", "2"), ("a", "3"), ("d", "1"), ("d", "3"))


var p = events.groupBy[String](x => x._1)

val pp = events.groupMap[String, String](x => x._1)(y => y._2)
