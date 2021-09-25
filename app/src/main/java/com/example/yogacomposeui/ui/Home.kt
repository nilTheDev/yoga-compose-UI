package com.example.yogacomposeui.ui


import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yogacomposeui.R
import com.example.yogacomposeui.data.InformativeCard
import com.example.yogacomposeui.data.informativeCards
import com.example.yogacomposeui.ui.theme.*

@Composable
fun Home() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Platinum)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {

        val defaultModifier = Modifier.padding(top = 24.dp)
        TopBar(defaultModifier)
        Header(defaultModifier)
        SearchBar(defaultModifier)
        ProgressSection(defaultModifier)
        ExtraCards(defaultModifier)
        Footer(defaultModifier)
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconWrapper(iconRes = R.drawable.ic_round_menu_24)
        Text(
            text = "Hello, Nilanjan",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        IconWrapper(iconRes = R.drawable.ic_round_notifications_none_24)
    }

}

@Composable
fun IconWrapper(
    @DrawableRes iconRes: Int,
    backgroundColor: Color = Color.White,
    iconTint: Color = TextBlack,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = "title",
            tint = iconTint,
            modifier = Modifier
                .padding(8.dp)
                .size(28.dp)
                .clickable { }
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Find Your",
            style = MaterialTheme.typography.h1,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "Workout Class",
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }


    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = {
            Text(
                text = "search for course",
                style = MaterialTheme.typography.body1,
                color = TextBlack.copy(0.3f),
                fontWeight = FontWeight.SemiBold,
            )
        },
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_round_search_24),
                contentDescription = "search icon",
                tint = BlueGreen,
                modifier = Modifier.size(32.dp)
            )
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedLabelColor = TextBlack.copy(0.1f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = BlueGreen.copy(0.8f)
        ),
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
    )
}

@Composable
fun ProgressSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SectionHeaderText(
            firstText = "Your Progress",
            secondText = "See Details"
        )
        ProgressDetails()
    }
}

@Composable
fun ProgressDetails(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        BlueGreen,
                        BoyBlue,
                        BlueBell,
                        RedSalsa.copy(0.7f),
                        OrangeRed.copy(0.8f),
                    )
                ),
                alpha = 0.8f
            )
            .padding(vertical = 24.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        var isClicked by remember { mutableStateOf(false) }

        CourseProgress(isClicked = isClicked)

        DetailsText(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            title = "Great!",
            subtitle = "Your course is almost completed"
        )

        ProgressSectionIcon { isClicked = true }
    }
}

@Composable
fun DetailsText(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    titleColor: Color = TextWhite,
    subtitleColor: Color = TextWhite
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h2,
            color = titleColor
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.body1,
            fontSize = 14.sp,
            fontWeight = FontWeight.Thin,
            color = subtitleColor
        )
    }
}

@Composable
fun CourseProgress(
    modifier: Modifier = Modifier,
    isClicked: Boolean = false,
) {

    val percentFinishedAnimated by animateFloatAsState(
        targetValue = if (isClicked) 0.7f else 0f,
        animationSpec = tween(1000)
    )

    Box(
        modifier = modifier
            .size(120.dp)
            .drawBehind {
                progressBackgroundCircle(
                    componentSize = Size(
                        width = size.width * 0.8f,
                        height = size.height * 0.8f,
                    ),
                    circleColor = TextWhite.copy(0.2f)
                )


                progressCurrentArc(
                    componentSize = Size(
                        width = size.width * 0.8f,
                        height = size.width * 0.8f
                    ),
                    arcColor = TextWhite,
                    percentFinished = percentFinishedAnimated
                )
            }
    ) {
        Text(
            text = "${(percentFinishedAnimated * 100).toInt()}%",
            style = MaterialTheme.typography.h2,
            color = TextWhite,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

fun DrawScope.progressCurrentArc(
    componentSize: Size,
    arcColor: Color,
    percentFinished: Float
) {
    drawArc(
        color = arcColor,
        size = componentSize,
        startAngle = 270f,
        sweepAngle = (360f * percentFinished),
        useCenter = false,
        style = Stroke(
            width = 25f
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) * 0.5f,
            y = (size.height - componentSize.height) * 0.5f
        )
    )

}

fun DrawScope.progressBackgroundCircle(
    componentSize: Size,
    circleColor: Color
) {
    drawCircle(
        color = circleColor,
        radius = componentSize.width / 2f,
        style = Stroke(
            width = 25f
        )
    )
}

@Composable
fun ProgressSectionIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(30.dp)
            .clip(CircleShape)
            .border(2.dp, TextWhite, CircleShape)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_more_horiz_24),
            contentDescription = "more icon",
            tint = TextWhite,
            modifier = Modifier
                .align(Alignment.Center)
                .size(20.dp)
                .clickable { onClick() }
        )
    }
}

@Composable
fun SectionHeaderText(
    firstText: String,
    secondText: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = firstText,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = secondText,
            style = MaterialTheme.typography.body1,
            color = BlueGreen,
        )
    }
}

@Composable
fun ExtraCards(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        InformativeCardCompose(
            information = informativeCards[0],
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f)
        )
        InformativeCardCompose(
            information = informativeCards[1],
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )
    }
}

@Composable
fun InformativeCardCompose(
    modifier: Modifier = Modifier,
    information: InformativeCard
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconWrapper(
            iconRes = information.iconResource,
            backgroundColor = information.backgroundColor,
            iconTint = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = information.title,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            fontSize = 15.sp
        )
        Text(
            text = information.value,
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        SectionHeaderText(
            firstText = "Recommended Class",
            secondText = "See More"
        )
        FooterCourseThumbnail(modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun FooterCourseThumbnail(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.yoga_girl_image),
            contentScale = ContentScale.Crop,
            contentDescription = "yoga girl image",
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .height(70.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        DetailsText(
            title = "Yoga",
            subtitle = "with Rachel Wisdom",
            titleColor = TextBlack.copy(0.8f),
            subtitleColor = TextBlack.copy(0.3f),
            modifier = Modifier.padding(8.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_favorite_black_24dp),
            contentDescription = "heart icon",
            tint = RedSalsa.copy(0.7f),
            modifier = Modifier.size(24.dp)
        )

    }
}

@Preview
@Composable
fun PreviewHome() {
    YogaComposeUITheme {
        Home()
    }
}