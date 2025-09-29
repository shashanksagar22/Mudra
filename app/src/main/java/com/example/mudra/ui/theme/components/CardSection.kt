package com.example.mudra.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mudra.R
import com.example.mudra.data.Cards
import com.example.mudra.ui.theme.theme.BlueEnd
import com.example.mudra.ui.theme.theme.BlueStart
import com.example.mudra.ui.theme.theme.OrangeEnd
import com.example.mudra.ui.theme.theme.OrangeStart
import com.example.mudra.ui.theme.theme.PurpleEnd
import com.example.mudra.ui.theme.theme.PurpleStart

val cards = listOf(

    Cards(
        cardType = "Visa",
        cardNumber ="2323 5445 7654 9870",
        cardName = "Credit" ,
        balance = 3200,
        color = getGradient(PurpleStart, PurpleEnd)
    ),
    Cards(
        cardType = "Rupay",
        cardNumber ="2322 5333 7654 7655",
        cardName = "Debit" ,
        balance =  13200,
        color = getGradient(BlueStart, BlueEnd)
    ),
    Cards(
        cardType = "Master",
        cardNumber ="7654 9870 2323 5445",
        cardName = "RupayCredit" ,
        balance = 113200,
        color = getGradient(OrangeStart, OrangeEnd)
    )
)
fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush{
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}
@Preview
@Composable
fun CardSection(){
    LazyRow {
        items(cards.size){index->
            CardItem(index)
        }
    }
}

@Composable
fun CardItem(
    index: Int
){
    val card = cards[index]
    var lastItemPaddingEnd= 0.dp
    if (index == cards.size-1){
        lastItemPaddingEnd = 16.dp
    }
    var image= painterResource(id = R.drawable.ic_visa)
    if (card.cardType == "Master"){
        image= painterResource(id = R.drawable.ic_mastercard)
    }

    Box(
        modifier = Modifier
            .padding(start= 16.dp, end= lastItemPaddingEnd)
    ){
        Column (
          modifier = Modifier
              .clip(RoundedCornerShape(25.dp))
              .background(card.color)
              .width(250.dp)
              .height(160.dp)
              .clickable{}
              .padding(vertical = 12.dp,
                       horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Image(painter = image,
                contentDescription = card.cardName,
                modifier = Modifier.width(60.dp))
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = card.cardName,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "₹ ${card.balance}",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = card.cardNumber,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
