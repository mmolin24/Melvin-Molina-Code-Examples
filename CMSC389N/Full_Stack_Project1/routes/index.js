var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.post('/processForm', function(req, res, next) {
  var temp = "";
  if (req.body.period == "OneToTwoYears")
    temp = "One To Two Years"

  if (req.body.period == "LessThanAYear")
    temp = "Less Than A Year"  
    
  if (req.body.period == "MoreThanTwoYears")
    temp = "More Than Two Years"

  if (req.body.period == "never")
    temp = "Never"  

  res.render('result', { title: 'Database Entry Confirmation',
                        firstName: req.body.firstname,
                        lastName: req.body.lastname,
                        phoneFirstPart: req.body.phoneFirstPart,
                        phoneSecondPart: req.body.phoneSecondPart,
                        phoneThirdPart: req.body.phoneThirdPart,
                        email: req.body.email,
                        age: req.body.age,
                        heightFeet: req.body.heightFeet,
                        heightInches: req.body.heightInches,
                        weight: req.body.weight,
                        highBloodPressure: req.body.highBloodPressure,
                        diabetes: req.body.diabetes,
                        glaucoma: req.body.glaucoma,
                        asthma: req.body.asthma,
                        none: req.body.none,
                        period: temp,
                        studyType: req.body.studyType,
                        firstFourDigits: req.body.firstFourDigits,
                        secondFourDigits: req.body.secondFourDigits,
                        comments: req.body.comments
                      });
  console.log(req.body);
});


module.exports = router;
