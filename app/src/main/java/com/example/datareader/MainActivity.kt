package com.example.datareader

import android.bluetooth.BluetoothSocket
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    private lateinit var b2: TextView
    private lateinit var b3: TextView
    private lateinit var b4: TextView
    private lateinit var b5: TextView
    private lateinit var b6: TextView
    private lateinit var b7: TextView
    private lateinit var b8: TextView

    private lateinit var c2: TextView
    private lateinit var c3: TextView
    private lateinit var c4: TextView
    private lateinit var c5: TextView
    private lateinit var c6: TextView
    private lateinit var c7: TextView
    private lateinit var c8: TextView

    private lateinit var d2: TextView
    private lateinit var d3: TextView
    private lateinit var d4: TextView
    private lateinit var d5: TextView
    private lateinit var d6: TextView
    private lateinit var d7: TextView
    private lateinit var d8: TextView

    private lateinit var e2: TextView
    private lateinit var e3: TextView
    private lateinit var e4: TextView
    private lateinit var e5: TextView
    private lateinit var e6: TextView
    private lateinit var e7: TextView
    private lateinit var e8: TextView

    private lateinit var f2: TextView
    private lateinit var f3: TextView
    private lateinit var f4: TextView
    private lateinit var f5: TextView
    private lateinit var f6: TextView
    private lateinit var f7: TextView
    private lateinit var f8: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b2 = findViewById<TextView>(R.id.b2)
        b3 = findViewById<TextView>(R.id.b3)
        b4 = findViewById<TextView>(R.id.b4)
        b5 = findViewById<TextView>(R.id.b5)
        b6 = findViewById<TextView>(R.id.b6)
        b7 = findViewById<TextView>(R.id.b7)
        b8 = findViewById<TextView>(R.id.b8)

        c2 = findViewById<TextView>(R.id.c2)
        c3 = findViewById<TextView>(R.id.c3)
        c4 = findViewById<TextView>(R.id.c4)
        c5 = findViewById<TextView>(R.id.c5)
        c6 = findViewById<TextView>(R.id.c6)
        c7 = findViewById<TextView>(R.id.c7)
        c8 = findViewById<TextView>(R.id.c8)

        d2 = findViewById<TextView>(R.id.d2)
        d3 = findViewById<TextView>(R.id.d3)
        d4 = findViewById<TextView>(R.id.d4)
        d5 = findViewById<TextView>(R.id.d5)
        d6 = findViewById<TextView>(R.id.d6)
        d7 = findViewById<TextView>(R.id.d7)
        d8 = findViewById<TextView>(R.id.d8)

        e2 = findViewById<TextView>(R.id.e2)
        e3 = findViewById<TextView>(R.id.e3)
        e4 = findViewById<TextView>(R.id.e4)
        e5 = findViewById<TextView>(R.id.e5)
        e6 = findViewById<TextView>(R.id.e6)
        e7 = findViewById<TextView>(R.id.e7)
        e8 = findViewById<TextView>(R.id.e8)

        f2 = findViewById<TextView>(R.id.f2)
        f3 = findViewById<TextView>(R.id.f3)
        f4 = findViewById<TextView>(R.id.f4)
        f5 = findViewById<TextView>(R.id.f5)
        f6 = findViewById<TextView>(R.id.f6)
        f7 = findViewById<TextView>(R.id.f7)
        f8 = findViewById<TextView>(R.id.f8)
    }

}
private const val TAG = "MY_APP_DEBUG_TAG"

// Defines several constants used when transmitting messages between the
// service and the UI.
const val MESSAGE_READ: Int = 0

// ... (Add other message types here as needed.)



class MyBluetoothService(
    // handler that gets info from Bluetooth service
    private val handler: Handler
) {

    private inner class ConnectedThread(private val mmSocket: BluetoothSocket) : Thread() {

        private val mmInStream: InputStream = mmSocket.inputStream
        private val mmBuffer: ByteArray = ByteArray(1024) // mmBuffer store for the stream

        override fun run() {
            var numBytes: Int // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                // Read from the InputStream.
                numBytes = try {
                    mmInStream.read(mmBuffer)
                } catch (e: IOException) {
                    Log.d(TAG, "Input stream was disconnected", e)
                    break
                }

                // Send the obtained bytes to the UI activity.
                val readMsg = handler.obtainMessage(
                    MESSAGE_READ, numBytes, -1,
                    mmBuffer)
                readMsg.sendToTarget()
            }
        }
    }
}