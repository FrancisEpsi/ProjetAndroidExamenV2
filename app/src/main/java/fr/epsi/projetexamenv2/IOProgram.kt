package fr.epsi.projetexamenv2

import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception


class IOProgram {
    fun load(myContext: Context, fileName: String?): Any? {
        return try {
            val fis = myContext.openFileInput(fileName)
            val ois = ObjectInputStream(fis)
            try {
                ois.readObject()
            } finally {
                try {
                    ois.close()
                } finally {
                    fis.close()
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun save(myContext: Context, obj: Any?, fileName: String?) : Boolean {
        var returnValue : Boolean = true
        try {
            val fos = myContext.openFileOutput(fileName, Context.MODE_PRIVATE)
            val oos = ObjectOutputStream(fos)
            try {
                oos.writeObject(obj)
                oos.flush()
            } finally {
                try {
                    oos.close()
                } finally {
                    fos.close()
                }
            }
        } catch (e: FileNotFoundException) {
            returnValue = false
            e.printStackTrace()
        } catch (e: Exception) {
            returnValue = false
            e.printStackTrace()
        }
        return returnValue
    }

    fun loadWithPath(myContext: Context?, fileName: String?): Any? {
        return try {
            val file = File(fileName)
            if (!file.exists()) return null
            val fis = FileInputStream(file) //myContext.openFileInput(fileName);
            val ois = ObjectInputStream(fis)
            try {
                ois.readObject()
            } finally {
                try {
                    ois.close()
                } finally {
                    fis.close()
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun saveWithPath(myContext: Context?, obj: Any?, fileName: String?) {
        try {
            val file = File(fileName)
            val fos = FileOutputStream(file)
            val oos = ObjectOutputStream(fos)
            try {
                oos.writeObject(obj)
                oos.flush()
            } finally {
                try {
                    oos.close()
                } finally {
                    fos.close()
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}