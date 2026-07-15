package com.gd.expoplus.data.model.ai



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.Color

object SuggestionGenerator {

    private val allSuggestions = listOf(

        Suggestion(
            Icons.Outlined.Analytics,
            Color(0xFF63E65C),
            "Summarize this month's spending"
        ),

        Suggestion(
            Icons.Outlined.Paid,
            Color(0xFF4FC3F7),
            "Where did I spend the most?"
        ),

        Suggestion(
            Icons.Outlined.CompareArrows,
            Color(0xFFFFB74D),
            "Compare this month with last month"
        ),

        Suggestion(
            Icons.Outlined.Lightbulb,
            Color(0xFFBA68C8),
            "How can I save more money?"
        ),

        Suggestion(
            Icons.Outlined.TrendingUp,
            Color(0xFFFF7043),
            "Show my spending trends"
        ),

        Suggestion(
            Icons.Outlined.CalendarMonth,
            Color(0xFF5C6BC0),
            "How much did I spend this week?"
        ),

        Suggestion(
            Icons.Outlined.CalendarToday,
            Color(0xFF26A69A),
            "How much did I spend today?"
        ),

        Suggestion(
            Icons.Outlined.AccountBalanceWallet,
            Color(0xFF66BB6A),
            "What's my average expense?"
        ),

        Suggestion(
            Icons.Outlined.ShoppingCart,
            Color(0xFFEF5350),
            "Which category costs me the most?"
        ),

        Suggestion(
            Icons.Outlined.BarChart,
            Color(0xFF29B6F6),
            "Show my top categories"
        ),

        Suggestion(
            Icons.Outlined.ReceiptLong,
            Color(0xFFFFCA28),
            "Show my recent expenses"
        ),

        Suggestion(
            Icons.Outlined.Savings,
            Color(0xFF81C784),
            "Where can I cut unnecessary expenses?"
        ),

        Suggestion(
            Icons.Outlined.Insights,
            Color(0xFFAB47BC),
            "Give me financial insights"
        ),

        Suggestion(
            Icons.Outlined.Timeline,
            Color(0xFF26C6DA),
            "Has my spending increased recently?"
        ),

        Suggestion(
            Icons.Outlined.QueryStats,
            Color(0xFFFF8A65),
            "Show my biggest transactions"
        ),

        Suggestion(
            Icons.Outlined.PriceCheck,
            Color(0xFF8BC34A),
            "What was my biggest purchase?"
        ),

        Suggestion(
            Icons.Outlined.Today,
            Color(0xFFFF7043),
            "How much did I spend yesterday?"
        ),

        Suggestion(
            Icons.Outlined.DateRange,
            Color(0xFF42A5F5),
            "Compare this week with last week"
        ),

        Suggestion(
            Icons.Outlined.AutoGraph,
            Color(0xFF7E57C2),
            "Which category is growing fastest?"
        ),

        Suggestion(
            Icons.Outlined.Percent,
            Color(0xFFEC407A),
            "What percentage goes to Food?"
        ),

        Suggestion(
            Icons.Outlined.PieChart,
            Color(0xFF26A69A),
            "Show category-wise spending"
        ),

        Suggestion(
            Icons.Outlined.LocalDining,
            Color(0xFFFFA726),
            "How much do I spend on Food?"
        ),

        Suggestion(
            Icons.Outlined.DirectionsCar,
            Color(0xFF29B6F6),
            "How much do I spend on Travel?"
        ),

        Suggestion(
            Icons.Outlined.ShoppingBag,
            Color(0xFFAB47BC),
            "How much do I spend on Shopping?"
        ),

        Suggestion(
            Icons.Outlined.Home,
            Color(0xFF66BB6A),
            "How much do I spend on Bills?"
        ),

        Suggestion(
            Icons.Outlined.TrendingDown,
            Color(0xFF42A5F5),
            "Am I spending less than before?"
        ),

        Suggestion(
            Icons.Outlined.Speed,
            Color(0xFFFF7043),
            "How is my spending pace?"
        ),

        Suggestion(
            Icons.Outlined.AttachMoney,
            Color(0xFF4CAF50),
            "Can I save ₹1000 this month?"
        ),

        Suggestion(
            Icons.Outlined.Stars,
            Color(0xFFFFD54F),
            "Give me personalized saving tips"
        ),

        Suggestion(
            Icons.Outlined.Lightbulb,
            Color(0xFFBA68C8),
            "Suggest a monthly budget"
        ),

        Suggestion(
            Icons.Outlined.AccountTree,
            Color(0xFF26C6DA),
            "Explain my spending habits"
        ),

        Suggestion(
            Icons.Outlined.EventAvailable,
            Color(0xFF66BB6A),
            "How much have I spent this month?"
        ),

        Suggestion(
            Icons.Outlined.EventBusy,
            Color(0xFFFF8A65),
            "How much did I spend last month?"
        ),

        Suggestion(
            Icons.Outlined.Assessment,
            Color(0xFF5C6BC0),
            "Analyze my spending pattern"
        ),

        Suggestion(
            Icons.Outlined.Psychology,
            Color(0xFFAB47BC),
            "What financial habits should I improve?"
        ),

        Suggestion(
            Icons.Outlined.AutoAwesome,
            Color(0xFFFFCA28),
            "What's one smart saving tip for me?"
        ),

        Suggestion(
            Icons.Outlined.EmojiObjects,
            Color(0xFF26A69A),
            "Where is my money going?"
        ),

        Suggestion(
            Icons.Outlined.ShowChart,
            Color(0xFFEF5350),
            "Which expenses should I reduce first?"
        ),

        Suggestion(
            Icons.Outlined.TrendingFlat,
            Color(0xFF42A5F5),
            "Is my spending stable?"
        ),

        Suggestion(
            Icons.Outlined.Analytics,
            Color(0xFF66BB6A),
            "Give me a quick financial summary"
        ),

        Suggestion(
            Icons.Outlined.MonetizationOn,
            Color(0xFFFFB300),
            "What's my average daily spending?"
        ),

        Suggestion(
            Icons.Outlined.CalendarViewWeek,
            Color(0xFF29B6F6),
            "Which week was the most expensive?"
        ),

        Suggestion(
            Icons.Outlined.CalendarViewMonth,
            Color(0xFF7E57C2),
            "Show this month's highlights"
        ),

        Suggestion(
            Icons.Outlined.WarningAmber,
            Color(0xFFFF7043),
            "Am I overspending?"
        ),

        Suggestion(
            Icons.Outlined.FavoriteBorder,
            Color(0xFFEC407A),
            "How healthy are my finances?"
        ),

        Suggestion(
            Icons.Outlined.Search,
            Color(0xFF26C6DA),
            "Find unusual spending"
        ),

        Suggestion(
            Icons.Outlined.Workspaces,
            Color(0xFF5C6BC0),
            "Which category needs attention?"
        ),

        Suggestion(
            Icons.Outlined.Bolt,
            Color(0xFFFFD54F),
            "Give me three quick money tips"
        ),

        Suggestion(
            Icons.Outlined.Insights,
            Color(0xFF4CAF50),
            "What can I improve this month?"
        ),

        Suggestion(
            Icons.Outlined.StarBorder,
            Color(0xFFFF8A65),
            "Tell me something interesting about my expenses"
        )
    )

    fun getSuggestions(): List<Suggestion> =
        allSuggestions
            .shuffled()
            .take(4)
}