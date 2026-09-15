package com.example.pemesenantiket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFFF8FAFC)
            ) {
                TicketBookingScreen()
            }
        }
    }
}

@Composable
fun TicketBookingScreen() {
    val ticketPrice = 25000
    var ticketCount by remember { mutableIntStateOf(1) }
    val totalPrice = ticketCount * ticketPrice

    fun formatRupiah(amount: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return format.format(amount).replace("Rp", "Rp").replace(",00", "")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F5F9))
    ) {
        // Header Section (Warna Biru dengan Sudut Melengkung)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF1E88E5),
                    shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
                )
                .padding(top = 48.dp, bottom = 28.dp, start = 24.dp, end = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.ConfirmationNumber,
                    contentDescription = "Ticket Icon",
                    tint = Color.White,
                    modifier = Modifier.size(44.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Pemesanan Tiket",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Pesan tiket dengan mudah!",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 13.sp
                )
            }
        }

        // Body Content (Daftar Card)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Card 1: Harga Tiket
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(text = "Harga Tiket", color = Color(0xFF64748B), fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = formatRupiah(ticketPrice),
                        color = Color(0xFF1976D2),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "per tiket", color = Color(0xFF94A3B8), fontSize = 12.sp)
                }
            }

            // Card 2: Jumlah Tiket (Counter)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(text = "Jumlah Tiket", color = Color(0xFF64748B), fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { if (ticketCount > 1) ticketCount-- },
                            modifier = Modifier
                                .size(44.dp)
                                .background(Color(0xFF2196F3), CircleShape)
                        ) {
                            Icon(Icons.Default.Remove, contentDescription = "Minus", tint = Color.White)
                        }

                        Box(
                            modifier = Modifier
                                .width(90.dp)
                                .height(44.dp)
                                .background(Color(0xFFF1F5F9), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ticketCount.toString(),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F172A)
                            )
                        }

                        IconButton(
                            onClick = { ticketCount++ },
                            modifier = Modifier
                                .size(44.dp)
                                .background(Color(0xFF2196F3), CircleShape)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Plus", tint = Color.White)
                        }
                    }
                }
            }

            // Card 3: Total Bayar
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(text = "Total", color = Color(0xFF64748B), fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = formatRupiah(totalPrice),
                        color = Color(0xFF1B873F),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Tombol Reset
            Button(
                onClick = { ticketCount = 1 },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reset",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "RESET",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}