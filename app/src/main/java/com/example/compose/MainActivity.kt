package com.example.compose

import SampleData
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController


//Global Variables
var userName = "Wayne"// placing a variable enables me to edit this in one place
var body = "You're learning some Kotlin!"

class MainActivity :
    ComponentActivity(/*Arguments (args) go in here!*/) { //curley braces are "the code"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        // Get the Bundle object that contains the arguments
        val bundle = intent.extras
        // Get the argument value by using the key
        val startDestination = bundle?.getString("startDestination")
        setContent {
            ComposeTheme {
                val startDestination = startDestination ?: "Home"
                mainDisplay(startDestination = startDestination)
            }
        }
    }
}

fun main() {}

data class Message(val author: String, val body: String)

// MAIN CardView for re-usability
@Composable
fun StandardCardView(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}

//In Jetpack Compose, you can use the Divider composable to create a line across the screen. Here’s how you can do it:
@Composable
fun CustomDivider(
    color: Color = Color.Gray,
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp
) {
    Divider(color = color, thickness = thickness, modifier = Modifier.padding(start = startIndent))
} //doesn't yet support gradients

@Composable
fun GradientDivider(
    colors: List<Color> = listOf(Color.LightGray, Color.DarkGray, Color.LightGray),
    thickness: Dp = 2.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(thickness)
            .background(Brush.horizontalGradient(colors))
    )
}



// TEST to see if I can preview the navScreens
class ComposableProvider: PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf("Home”, “Track”, “Target”, “Trim”, “Train")
}

// ~~~~~~~~~App Bar & Navigation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainDisplay(startDestination: String /*for the preview navScreen*/) {
    val navController = rememberNavController()
    //val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier
                .shadow(3.dp),

                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu Draw")
                    }
                },
                title = { Text("My Income", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                actions = {
                    IconButton(onClick = { navController.navigate("Settings") }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                    IconButton(onClick = { /* Handle user profile icon click */ }) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "User Profile")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.shadow(3.dp),
            ) {
                NavigationBar {
                    val items = listOf("Home", "Track", "Target", "Trim", "Train")
                    val icons = listOf(
                        Icons.Filled.Home,
                        Icons.Filled.Info,
                        Icons.Filled.DateRange,
                        Icons.Filled.Lock,
                        Icons.Filled.Star
                    )

                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(icons[index], contentDescription = item) },
                            label = { Text(item) },
                            selected = navController.currentDestination?.route?.startsWith(item) == true, // Adjusted selection logic
                            onClick = {
                                navController.navigate(item)
                            }
                        )
                    }
                }
            }
        },

        //TODO figure out the menu drawer.

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

                composable("Settings") { SettingsScreen() }
            }
        }
    }
}


@Composable
fun SettingsScreen() {

    LazyColumn(Modifier.fillMaxSize()){
        items(3){ index ->
            when (index) {
                0 -> Text(text = "This is the Settings Screen", modifier = Modifier.padding(3.dp))



                //TODO implement the pay-dates


            }
        }
    }
}

@Composable //Unable to work this out at this time.
fun NavigationDrawer(){
    Column(modifier = Modifier.fillMaxSize()) {
    Text("Placeholder Header", modifier = Modifier.padding(16.dp))
    Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.clickable { /* Handle click on item 1 */ }) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
                Text("Placeholder Item 1")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.clickable { /* Handle click on item 2 */ }) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Filled.Info, contentDescription = "Info")
                Text("Placeholder Item 2")
            }
        }
        // Add more placeholder items...
    }
}

    @Composable
    fun HomeScreen() {
        LazyColumn(//LC here to provide vertical scrolling
            Modifier
                .fillMaxWidth(0.95f)//sets the column to 95% width
                .fillMaxHeight()//sets the screen to fill max height for scrolling
        ){
            items(7){ index ->
                when (index) {
                    0 -> Text(text = "This is the Home Screen", modifier = Modifier.padding(3.dp))

                    1 -> PayCycleCard()

                    2 -> StandardCardView{ Text("Second Card - to re-use!")}

                    3 -> CustomDivider()

                    4 -> StandardCardView{ Text("Third Card - to re-use!")}

                    5 -> GradientDivider()

                    6 -> StandardCardView{ Text("Fourth Card - to re-use!")}

                }
            }
        }
    }

    @Composable
    fun TrackingScreen() {
        Column(Modifier.fillMaxSize()) {
            var visible by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "This is the Tracking Screen", modifier = Modifier
                        .padding(12.dp),
                    fontStyle = FontStyle.Normal,
                    fontSize = 20.sp
                )


                IconButton(onClick = { visible = !visible }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Information Icon")
                }
            }

            AnimatedVisibility(visible = visible) {
                Card {
                    Text(
                        text = "! Here I will display the dates paid within the year, grouped by month." +
                                "\nThe user will be able to select the cards which expand. " +
                                "\nOnce expanded they can edit (button opening bottomSheet." +
                                "\nOr, they can open a new screen/card to edit the information for that month. Such as adding bill due dates etc...",
                        modifier = Modifier.padding(12.dp),
                        fontStyle = FontStyle.Italic
                    )
                }
            }



            Spacer(modifier = Modifier.width(4.dp))

            Conversation(SampleData.conversationSample) // pass the list of messages as a parameter
        }//I needed to take out the second LazyColumn because it creates an infinite list, Conversation() is a LazyColumn!

    }

    @Composable
    fun TargetingScreen() {
        LazyColumn(Modifier.fillMaxSize()){
            items(3){ index ->
                when (index) {
                    0 -> Text(text = "This is the Targeting Screen", modifier = Modifier.padding(3.dp))

                    1 -> PayCycleCard()

                    //TODO implement the pay-dates


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


@Composable //TRACKING remember that this message card is a little different to the pay-cycle one
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
@Composable /*for the preview navScreen*/
fun MainDisplayPreview(@PreviewParameter(ComposableProvider::class) startDestination: String) {
mainDisplay(startDestination)
}//This takes in the class and enables the preview to accept the alternate screen.

