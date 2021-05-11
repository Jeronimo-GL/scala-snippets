import $dep.`org.json4s::json4s-native:3.7.0-M8`

import org.json4s._
import org.json4s.native.JsonMethods._

/*
 Cuando parseamos una cadena JSON obtenemos un JValue al que le podemos hacer preguntas.
 */
val json = parse("""
  { "name": "joe",
    "children": [
      {
        "name": "Mary",
        "age": 5
     },
      {
        "name": "Mazy",
        "age": 3
      }
    ]
  }
""")


/*
 Podemo usar for comprehensions para extraer la información.
 */
for {
  JObject(child) <- json
  JField("age", JInt(age))  <- child
} yield age


/*
 También tenemos la opción compacta. 
*/
json \ "name"


/*
 Pero hay que tener cuidado porque puede devolver JNothing
*/
json \ "address"


/*
 Si queremos todos los campos con un nombre en particular sin importarnos la profundidad
*/
json \\ "name"
