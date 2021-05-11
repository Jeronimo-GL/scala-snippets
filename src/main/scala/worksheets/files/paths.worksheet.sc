import java.io.File
lazy val filePath: String = "data/events/transfers/chats/transfer.jsonl"


val f = new File(filePath)

f.getAbsolutePath()
f.getCanonicalPath()
f.getName()
f.getPath()
f.getParent()

f.getName().split('.')(0)

new File(filePath).getName().split('.')(0)
