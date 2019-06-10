var count = 0;
function mine() {

    ++count;

    if(count === 3){

        var confirm = prompt("你是本人？");

        if(confirm === "530412"){

            alert("你好，lilnl！")

        }

        count = 0;

    }
}
function myIntroHidden() {

    document.getElementById("div_intro").style.visibility = "hidden";

}

function myIntro() {

    document.getElementById("div_intro").style.visibility = "visible";
}

function androidImageOver() {

    document.getElementById("img_android").src = "../resource/android_over.svg";

}

function androidImageOut() {

    document.getElementById("img_android").src = "../resource/android.svg";

}