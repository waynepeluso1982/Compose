package com.example.compose

import SampleData
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.rememberNavController


//Global Variables
var userName = "Wayne"// placing a variable enables me to edit this in one place
var body = "You're learning some Kotlin!"

/*I am placing the variables here for now. They may not be best placed within the global space.
* Should the app calculate these things every startup or, should we place them in a database
*/

class MainActivity :
    ComponentActivity(/*Arguments (args) go in here!*/) { //curley braces are "the code"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                mainDisplay()

//                Box(modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.LightGray) //ads color to the BG
//                    .padding(horizontal = 4.dp, vertical = 2.dp),//ads padding to the sides
//                    contentAlignment = Alignment.Center) {
//
//                    Column(
//                        modifier = Modifier.fillMaxSize(),
//                        verticalArrangement = Arrangement.Top,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Spacer(modifier = Modifier.height(2.dp))
//
//                        Surface(
//                            modifier = Modifier
//                                .wrapContentSize(Alignment.TopCenter)
//                                .width(IntrinsicSize.Max)
//                                .fillMaxWidth(0.8f), // 80% of screen
//                            shape = MaterialTheme.shapes.small,
//                            shadowElevation = 1.dp
//                        ) {
//                            PayCycleCard(
////                                msg = Message(userName, body),
////                                dateInfo = DateInfo(financialYear)
//                            )//TODO include the variables for this card
//                        }
//
//                        Spacer(modifier = Modifier.height(4.dp))
//
//                        //This is the conversation list!
//                        Surface(shape = MaterialTheme.shapes.small, shadowElevation = 1.dp) {
//                            Conversation(SampleData.conversationSample)
//                        }
//                    }
//                }
            }
        }
    }
}

fun main() {}

data class Message(val author: String, val body: String)

// MAIN CardView for reusability
@Composable
fun CardView(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}




// ~~~~~~~~~App Bar & Navigation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainDisplay() {
    val navController = rememberNavController()
    //val items = listOf("Homes", "Track", "Target", "Trim", "Train")
    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier
                .shadow(3.dp),

                navigationIcon = {
                    IconButton(onClick = { /*TODO Learn navigation drawer*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu Draw"
                        )
                    }
                },
                title = { Text("My Income", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                actions = {
                    IconButton(onClick = { /* Handle settings icon click */ }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                    IconButton(onClick = { /* Handle user profile icon click */ }) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "User Profile")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.shadow(3.dp),
            ) {
                val items = listOf("Home", "Track", "Target", "Trim", "Train")
                val icons = listOf(Icons.Filled.Home, Icons.Filled.Info, Icons.Filled.DateRange, Icons.Filled.Lock, Icons.Filled.Star)

                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = navController.currentDestination?.route == item, //Can also be false //TODO: Set this based on the current route
                        onClick = {
                            navController.navigate(item)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            NavHost(navController, startDestination = "Home", Modifier.padding(innerPadding)) {
                composable("Home") { HomeScreen() }
                composable("Track") { TrackingScreen() }
                composable("Target") { TargetingScreen() }
                composable("Trim") { TrimmingScreen() }
                composable("Train") { TrainingScreen() }
            }
        }
    }
}
    @Composable
    fun HomeScreen() {
        LazyColumn(
            Modifier
                .fillMaxWidth(0.95f)//sets the column to 95% width
                .fillMaxHeight()//sets the screen to fill max height for scrolling
        ){
            items(3){ index ->
                when (index) {
                    0 -> Text(text = "This is the Home Screen", modifier = Modifier.padding(3.dp))

                    1 -> PayCycleCard()


                }
            }
        }
    }

    @Composable
    fun TrackingScreen() {
        Column(Modifier.fillMaxSize()) {
            Text(text = "This is the Tracking Screen", modifier = Modifier.padding(3.dp))
            Conversation(SampleData.conversationSample) // pass the list of messages as a parameter
        }//I needed to take out the second LazyColumn because it creates an infinite list

//        LazyColumn(Modifier.fillMaxSize()){
//            items(3){ index ->
//                when (index) {
//                    0 -> Text(text = "This is the Tracking Screen", modifier = Modifier.padding(3.dp))
//
//                    1 -> Conversation(SampleData.conversationSample)
//                }
//            }
//        }
    }

    @Composable
    fun TargetingScreen() {
        LazyColumn(Modifier.fillMaxSize()){
            items(3){ index ->
                when (index) {
                    0 -> Text(text = "This is the Targeting Screen", modifier = Modifier.padding(3.dp))

                    1 -> PayCycleCard()


                }
            }
        }
    }

    @Composable
    fun TrimmingScreen() {
        LazyColumn(Modifier.fillMaxSize()){
            items(3){ index ->
                when (index) {
                    0 -> Text(text = "This is the Trimming Screen", modifier = Modifier.padding(3.dp))


                }
            }
        }
    }
    @Composable
    fun TrainingScreen() {
        LazyColumn(Modifier.fillMaxSize()){
            items(3){ index ->
                when (index) {
                    0 -> Text(text = "This is the training Screen", modifier = Modifier.padding(3.dp))

                    1 -> PayCycleCard()

                }
            }
        }
    }
// ~~~~~~~~~App Bar & Navigation

@Composable
fun PayCycleCard() {
    // Financial Year
    val dateToday = LocalDate.now()
    val isLeapYear = dateToday.year % 4 == 0 // Check if the current year is a leap year
    val fyStartDate = LocalDate.of(dateToday.year, 7, 1)
    val fyEndDate = LocalDate.of(dateToday.year + 1, 6, 30)
    val financialYear = if (dateToday.monthValue < 7) "${dateToday.year - 1}-${dateToday.year.toString().substring(2)}" else "${dateToday.year}-${(dateToday.year + 1).toString().substring(2)}"
    val payCycle = "Fortnightly"
    val fortnight = "12" // TODO: Insert the pay cycle info (weekly, fortnightly, monthly)
    val dateFormatted = DateTimeFormatter.ofPattern("dd MMM yy", Locale.ENGLISH)
    val ddDateFormatted = DateTimeFormatter.ofPattern("dd")
    val mmmDateFormatted = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH)

    Text(text = "Pay Cycle", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))

    Spacer(modifier = Modifier.height(10.dp))

    Surface(shape = RoundedCornerShape(12.dp),
        color = Color.LightGray,
        modifier = Modifier
        .shadow(2.dp),

        //.fillMaxWidth(0.95f) //this sets the card to 95% of the column width
    ) {
        Row(
            modifier = Modifier.padding(all = 11.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Column( //LEFT Column
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "FY: $financialYear", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Fortnight: 12", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Cycle: Fortnightly", style = MaterialTheme.typography.bodyMedium)
            }

            Column(
//CENTER Column
                modifier = Modifier.weight(1f),

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 1.dp)
                        .shadow(2.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = dateToday.format(ddDateFormatted),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(1.dp)
                        )
                        Text(
                            text = "dd Mmm - dd Mmm",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(1.dp)
                        )//perhaps the width is to wide for the box?!
                        //TODO adjust second text to correct format
                    }
                }
            }

            Column(//RIGHT Column
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Days to Pay: 8", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Other random info", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Deposit: $2,420.61", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun UpcomingBillCard(){

}


@Composable //remember that this message card is a little different to the pay-cycle one
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        //Track if it is expanded
        var isExpanded by remember { mutableStateOf(false) }
        // Surface color change
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = ""
        )
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}

//Final section of the tutorial
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun MainDisplayPreview() {
mainDisplay()
}