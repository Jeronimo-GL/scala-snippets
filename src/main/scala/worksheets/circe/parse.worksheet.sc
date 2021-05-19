import $dep.`io.circe::circe-parser:0.12.3`

import io.circe._
import io.circe.parser._

/*
   Parseo sencillo.

  Podemos obtener fácilmente un objeto Json.
*/

val rawJson: String = """
{
  "foo": "bar",
  "baz": 123,
  "list of stuff": [ 4, 5, 6 ]
}"""

val parseResult = parse(rawJson)

val r = parseResult.getOrElse(Json.Null)


/*
  Si queremos movernos por un objeto Json parseado tenemos que usar un HCursor
 */

val jsonStr: String = """
  {
    "id": "c730433b-082c-4984-9d66-855c243266f0",
    "name": "Foo",
    "counts": [1, 2, 3],
    "values": {
      "bar": true,
      "baz": 100.001,
      "qux": ["a", "b"]
    }
  }
"""

val doc: Json = parse(jsonStr).getOrElse(Json.Null)

val cursor: HCursor = doc.hcursor

val baz: Decoder.Result[Double] = cursor.downField("values").downField("baz").as[Double]

// baz es de tipo Decoder.Result, que es como un Either.
baz match {
  case Right(v) => v
  case Left(e) => 0.0
}




