let number = 0;

function incrementValue() {
    number++;
    document.getElementById("num-value").innerHTML = number.toString();
}

function hideText() {
    if (document.getElementById("hide-button").value !== "Reveal text") {
        document.getElementById("hide-button").value = "Reveal text";
        document.getElementById("hidden-text").hidden = true;
    } else {
        document.getElementById("hide-button").value = "Hide text";
        document.getElementById("hidden-text").hidden = false;
    }
}

const wordArr = ["Hello", "World", "Python", "Tihlde", "Trympedia", "Another", "Word", "I", "Could", "Add", "More", "But", "It", "Feels", "Unnecessary"];

const randomWord = () =>wordArr[Math.floor(Math.random() * wordArr.length)];
function generateRandomList() {
   document.getElementById("word1").innerHTML = randomWord();
   document.getElementById("word2").innerHTML = randomWord();
   document.getElementById("word3").innerHTML = randomWord();
}

function attemptLogin() {
    if (document.getElementById("username").innerHTML === "1" && document.getElementById("password").innerHTML === "2") {
        document.getElementById("requirements").style.visibility;
    } else {
        document.getElementById("requirements").style.visibility = "visible";
    }
}