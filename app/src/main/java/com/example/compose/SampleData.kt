import com.example.compose.Message
import java.text.NumberFormat

/**
 * SampleData for Jetpack Compose Tutorial
 */

val income = 2451.65
val income2Monthly = income * 2
val income3Monthly = income * 3

val childSupport = 2456.58
val citiBankDebt = 245.00

val format = NumberFormat.getCurrencyInstance()
val formatted2Income = format.format(income2Monthly)
val formatted3Income = format.format(income3Monthly)
val formattedBills = format.format(childSupport + citiBankDebt)

/*Just using these val to organise thoughts. I will need to include the correct methods later.*/


//TODO consider currency class
/*Consider the card to show each of the 12 months (FY or Fiscal). You can then group the income per month
* as bills are typically arranged by month, if not paid by the month. */
/*
* format.currency = Currency.getInstance("USD")*/

object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Jan 24 (04 - 18)",//Grouped by month, list the date
            "$formatted2Income | Bills  -$formattedBills" +
                    "\nB - Child Support due 07 Jan ($$childSupport)" +
                    "\nB - Citi Bank due 08 Jan ($$citiBankDebt)"
        ),
        Message(
            "Feb 24 (1 - 15 - 29)",
            "$formatted3Income | Bills  -$formattedBills" +
                    "\nB - Child Support due 07 Jan ($$childSupport)" +
                    "\nB - Citi Bank due 08 Jan ($$citiBankDebt)"
        ),
        Message(
            "Lexi",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Lexi",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Lexi",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "Lexi",
            "It's available from API 21+ :)"
        ),
        Message(
            "Lexi",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Lexi",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Lexi",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Lexi",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Lexi",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Lexi",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Lexi",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}
