// place your javascript code here

window.onload = function () {
    document.getElementById("firstname").focus();
}

function validateForm() {

    alertStr = ""

    // calling outside functions to continue validation
    alertStr += phones();
    alertStr += conditions();
    alertStr += radios();
    alertStr += studyID();

    if (alertStr == "") {
        //find a way to submit the form
        return confirm("Do you want to submit the form data?")
    } else {
        window.alert(alertStr);
        return false;
    }

}

function clearForm() {
    document.getElementById("form").reset();
}


function conditions() {

    var returnStr = "";
    var none = document.getElementById("none");

    var others = false;

    if (document.getElementById("highBloodPressure").checked || document.getElementById("diabetes").checked || document.getElementById("glaucoma").checked || document.getElementById("asthma").checked) {
        others = true;
    }

    if (others && none.checked) {
        returnStr += "Invalid conditions selection\n";

    } else if (!others && !none.checked) {
        returnStr += "No conditions selected\n";
    }
    return returnStr;
}


function radios() {

    var neverRadio = document.getElementById("never").checked
    var lessRadio = document.getElementById("lessThan").checked
    var oneTwoRadio = document.getElementById("oneTwo").checked
    var moreThanRadio = document.getElementById("moreThan").checked
    var str = "";
    // checks if none of the radios have been selected
    if (!neverRadio && !lessRadio && !oneTwoRadio && !moreThanRadio) {
        str += "No time period selected \n"
    }

    return str;
}

function studyID() {

    // use helper function to double check the study 
    var a = document.getElementById("firstFourDigits").value
    var b = document.getElementById("secondFourDigits").value
    var str = "";

    if ((isNaN(a.slice(1)) || isNaN(b.slice(1))) || a.slice(1).length != 3 || b.slice(1).length != 3 || !(a.charAt(0) == 'A') || !(b.charAt(0) == 'B')) {
        str += "invalid study id\n"
    }

    return str;
}


function phones() {

    var phoneNum = ""
    var str = ""
    phoneNum += document.getElementById("phoneFirstPart").value;
    phoneNum += document.getElementById("phoneSecondPart").value;
    phoneNum += document.getElementById("phoneThirdPart").value;


    if (isNaN(phoneNum) || phoneNum.length != 10) {
        str += "Invalid Phone Number \n";
    }
    return str;
}