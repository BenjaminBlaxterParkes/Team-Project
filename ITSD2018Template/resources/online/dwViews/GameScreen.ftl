<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>

		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">

			<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
				
		<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		
	<style>
	h1, h5, h6, p {
	margin: 0;
	padding: 0;
}

h2 {
	font-size: 18pt;
	margin: 0 0 0 40px;
	padding:  0 0 10px 0;
	color: #F4F4F4;
	text-align: left;
}

h3 {
	font-size: 150%;
	margin: 0 0 0 40px;
	padding: 0 0 10px 0;
	text-align: left;
	color: #ffa500;

}

h4{
	font-size: 120%;
	margin: 0 0 0 40px;
	padding: 0 0 40px 0;
	text-align: left;
	color: #40e0d0;
}

.col-xs-2{
height: 450px;
}

#cardtitle{
	font-size: 17pt;
	margin: auto;
	padding: 25px 10px 10px 55px;
	color: #F4F4F4;
	text-align: left;
}

button {
	font-size: 160%;
	margin: 5px 5px 0px 20px;
	padding: 2px 180px 2px 2px;
}

#catbutton {
	font-size: 15px;
	text-align: left;

	margin: 5px 5px 0px 15px;
	width: 200px;
	height: 25px;
}

p {
	margin: 0 0 1em 0;
	font-size: 93%;
	line-height: 1.5em;
}

body {
	font-family: Helvetica, Arial, sans-serif;
	padding: 0;
	margin: 0;
	background-color: #2B7A78;

}

img {
	max-width: 100%;
	height: auto;

}

/* Section Inner */
div.section-inner {
	max-width: 1100px;
	padding: 0;
	margin: 0 auto;
}

/* logo */
div.logo {
padding: 10px 40px 10px 0px;
max-width: 28%;
margin: 0 ;
}

/* Header */
div.header {
	background-color: #3F3F3F;
	padding: 0;
}
/* off blue #31708E */
/* truquoise #85DCB  3F3F3F */


/* Navigation bar*/
div.nav {
	background-color: #D6D6D6;
}

div.nav ul {
	margin: 0;
	padding: 0;
}

div.nav ul li {
	list-style: none;
	float: left;
	font-size: 93%;
}

div.nav ul li a:link,
div.nav ul li a:visited {
	display: block;
	padding: 10px 15px;
	text-decoration: none;
	color: #3F3F3F;
	border-right: 1px solid #bababa;
}

/* Body Content */
div.body-content {
	padding: 50px 0;
	background-image: url(https://raw.githubusercontent.com/aive/toptrump_images/master/background_ray.png);
	background-size: cover;
}

/* Main Column */
div.main {
	width: 65%;
	float: left;
	margin-right: 5%;
}

.footer {
	position: fixed;
	height: 35px;
	width: 100%;
  right: 0;
  bottom: 0;
  left: 0;
  padding-top: 20px
    padding-bottom: 20px;
  background-color: #3F3F3F;
  text-align: center;
}
	
</style>
</head>
<body>

<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

	<title>Responsive Top Trumps</title>

	<meta name="viewport" content="width=device-width">

	<!-- css -->
	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/responsive.css" media="screen and (max-width: 900px)">


</head>

   <body onload="initialize()">
	
	
	<!-- header -->
	<div class="header"><div class="section-inner">
	<div class="logo"><div class="col order-first">
	<img src="https://raw.githubusercontent.com/aive/toptrump_images/master/logo_tt.png" alt="logo" />
	</div></div>
	</div></div>
	<!--/header-->

<!-- nav -->
	<div class="nav"><div class="section-inner">
	
				<!-- DISPLAY IF THERE'S BEEN A DRAW -->
				<div id="drawDisplay" class="drawDisplay">
				<h1 id="display" class="display"></h1>
				</div>
				
				
				<!-- SELECT CATEGORY -->
				<div id="categories" class="categories">
				<div></div>
				</div> 		
				
				<!-- DISPLAY NUMBER OF CARDS IN THE COMMUNAL PILE -->
				<div id="csInP" class="csInP">
				<h3 id="display" class="display"></h3>
				</div>
				
				
				<!-- DISPLAY HOW MANY CARDS IN EACH PLAYER'S HANDS -->
				<div id="playersHands" class="playersHands">
				<p></p>
				</div>
				
				
				<!-- PLAY ROUND AND CONTINUE BUTTON -->
				<div id="btn" class="btn">
				<button id="pr" class="pr" onclick="playRound()">Play Round</button>
				<button id="cnt" class="cnt" onclick="changeState()">Continue? </button>
				</div>
				
				
				<!-- ACTIVE PLAYER DISPLAY -->
				<!-- This will be the player display. It displays messages relevant to the player-->
				<div id="player" class="player">
				<h3 id="pDisp" class="pDisp"> </h3> 
				<hr>
				</div>
	</div></div><!--/nav-->
	
	<div id="killSwitch">
	<!-- THE KILLSWITCH BUTTON -->
				<button onclick="killGame()"><h3>END THE GAME???</h3></button>
	</div>

	<!-- body-content -->
	<div id="bodyCnt" class="body-content"><div class="section-inner">

			<div class="container">
			<div class="row">
			
			
			
			<div id="clmF" class="col-sm-4" style="background: url(https://raw.githubusercontent.com/aive/toptrump_images/master/card_blank.png); background-size: 260px; background-repeat: no-repeat; ">
			  <p id="cardtitle"></p>
			  <img src="https://raw.githubusercontent.com/aive/toptrump_images/master/Fry.jpg" width="228px"/>
			   <h3>You</h3>
			   <h4></h4>
				<!-- PLAYER CARD DISPLAY
				<div id="card" class="card">
				<p></p>
				<h3></h3>
				<h4></h4> -->
			</div>
			
			
			
			<!-- AI CARDS -->
			
			<div id="c1" class="col-xs-2">
				<!-- CARD 1 DISPLAY -->
				<img src="https://raw.githubusercontent.com/aive/toptrump_images/master/card_back_small.png" />
				<h3></h3>
				<h2></h2>
				<h4> 1. Combat: </br>
					2. Lewdness: </br>
					3. Agility: </br>
					4. Lunacy: </br>
					5. IQ: </h4>
			</div>
				<!-- CORTANA CARD DISPLAY -->
			<div id="c2" class="col-xs-2">
				<img src="https://raw.githubusercontent.com/aive/toptrump_images/master/card_back_small.png" />
				<h3></h3>
				<h2> </h2>
				<h4> 1. Combat: </br>
					2. Lewdness: </br>
					3. Agility: </br>
					4. Lunacy: </br>
					5. IQ: </h4>
			</div>
				<!-- GLADoS CARD DISPLAY -->
			<div id="c3" class="col-xs-2">
				<img src="https://raw.githubusercontent.com/aive/toptrump_images/master/card_back_small.png" />
				<h3></h3>
				<h2> </h2>
				<h4> 1. Combat: </br>
					2. Lewdness: </br>
					3. Agility: </br>
					4. Lunacy: </br>
					5. IQ: </h4>
				</div>
				<!-- MARVIN CARD DISPLAY -->
			<div id="c4" class="col-xs-2">
				<img src="https://raw.githubusercontent.com/aive/toptrump_images/master/card_back_small.png" />
				<h3></h3>
				<h2> </h2>
				<h4> 1. Combat: </br>
					2. Lewdness: </br>
					3. Agility: </br>
					4. Lunacy: </br>
					5. IQ: </h4>
				</div>
			
			<!-- main -->
			<div class="main mobile-collapse">


			</div><!--/main-->

			</div><!--CLOSES THE CONTAINER -->
			
			</div><!--/two-columns-->
	
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initialize() {
				
				//WHEN THE GAME STARTS WE 
				//CHOOSE FIRST PLAYER
				chooseFirstPlayer();
				hideDD();
				hideCNT();
				hideAICards();
				getAICards();
				getAllHands();
				database();
				hideKillSwitch();
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
		//GET NUMBER OF CARDS IN PLAY
		//This is going to get cards in play. 
		//WHAT we need is cards in pile...
		//
		//
		function getCardsInPlay(){
		var x = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardsInPlay");
		x.onload = function(e){
			var rsp = x.response;
			let csInP = document.querySelector('.csInP');
			let cat_one = document.querySelector('h3');
			cat_one.innerHTML = rsp;
			};
		x.send();
		}
		
		
		
		//GETS CARDS FROM EACH PLAYER'S HAND
		function getAllHands(){
			var x = createCORSRequest('GET', "http://localhost:7777/toptrumps/getAllHands");
			x.onload = function(e){
			var rsp = x.response;
			let playersHands = document.querySelector('.playersHands');
			let hands = playersHands.querySelector('p');
			hands.innerHTML = "Number of cards in players hands: </br>" + rsp;
			};
			x.send();
		}
		
		
		
		
		//HIDE THE CNT BUTTON
		function hideCNT(){
		let hide = document.querySelector('.btn');
		let cat_one = document.querySelector('.cnt');
		cat_one.hidden = true;
		}
		
		
		//SHOW THE PLAYROUND BUTTON
		function showPR(){
			let show = document.querySelector('.btn');
			let cat_one = document.querySelector('.pr');
			cat_one.hidden = false;
		}
		
		
		
		//HIDE AI CARDS
		function hideAICards(){
		var hide = document.getElementsByClassName('col-xs-2');
		var i;
		for (i = 0; i < hide.length; i++) {
		hide[i].style.visibility = "hidden";
		}
		}
		
		//SHOW AI CARDS
		function showAICards() {
    	var x = document.getElementById("bodyCnt").querySelectorAll(".col-xs-2");
    	var i;
   		for (i = 0; i < x.length; i++) {
        x[i].style.visibility = "visible";
		}
		}
		
		
		
		
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			



			// This calls the helloWord REST method from TopTrumpsRESTAPI
			function helloWord(word) {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}



			//------ CHOOSE FIRST PLAYER --------
			//SHOWS THE NAME PLAYER
			//SHOWS THE TOP CARD DETAILS
			//CALLS IFHUMAN() TO EITHER HIDE OR DISPLAY THE PR BUTTON/CAT BUTTONS
			//-----------------------------------
			//DECLARE FUNC
			function chooseFirstPlayer(){
			//CORS REQ
			var gP = createCORSRequest('GET', "http://localhost:7777/toptrumps/chooseFirstPlayer");
			if (!gP) {alert("CORS not supported");}
			gP.onload = function(e){
				var responseText = gP.response; //THIS SHOULD BE THE NAME OF THE FIRST PLAYER
				let player = document.querySelector('.player');
				let cat_one = player.querySelector('h3');
				cat_one.innerHTML = "Good news everyone " + responseText + " is first...";
				ifHuman(responseText);
			};
			gP.send();
			//SHOW PLAYER CURRENT CARD
			peek();
			}
			
			
			
			
			//IS THE ACTIVE PLAYER HUMAN?
			function ifHuman(param1){
			//OLD TEST alert(param1+"has been passed");
			if(param1 === "player"){
				let categories = document.querySelector('.categories');
				let tmp = categories.querySelector('div');
				tmp.innerHTML = "<div><button onclick=\"chooseCat(1)\">Combat</button> <button onclick=\"chooseCat(2)\">Lewdness</button> <button onclick=\"chooseCat(3)\">Agility</button> <button onclick=\"chooseCat(4)\">Lunacy</button> <button onclick=\"chooseCat(5)\">Intelligence</button>	</div>";	
				let btn = document.querySelector('.btn');
				let pr = btn.querySelector('.pr');
				pr.hidden = true;
				//HIDE THE CONTINUE BUTTON
				hideCNT();
			}
				else { let categories = document.querySelector('.categories');
						let tmp = categories.querySelector('div');
						tmp.innerHTML = "<div> </div>";
					}
			}
			
			
			
			
			
			//GET WHO WON/WHO IS THE ACTIVE PLAYER
			//GET THE ACTIVE PLAYER NAME
			function getWhoWon(){
							//MAKE CORS
							var wW = createCORSRequest('GET', "http://localhost:7777/toptrumps/getWhoWon");
							//CHECK CORS 
							if (!wW) {alert("CORS not supported");}
			//ON LOAD CREATE
			//A VAR USING
			//THE RESPONSE TEXT
			//WHICH SHOULD BE 
			//THE ACTIVE PLAYER, IE THE WINNER OF THE PREVIOUS ROUND, NAME AS A STRING
			wW.onload = function(e){
					var whoWon = wW.response;
					//SAY WHO WON
					let player = document.querySelector('.player');
					let cat_one = player.querySelector('h3'); 
					cat_one.innerHTML = "Great, that chump " + whoWon +" won";
					//HIDES THE PLAY ROUND BUTTON
					let button = document.querySelector('.btn');
					let pr = button.querySelector('.pr');
					pr.hidden = true;
					//SHOW THE CONTINUE BUTTON
					let but = button.querySelector('.cnt');
					but.hidden = false;
					//THIS SENDS THE NAME OF THE PLAYER
					//TO THE IFHUMAN CHECK.
					//IF IT'S THE PLAYER SHOW THE BUTTONS
					ifHuman(whoWon);
				}
			wW.send();
			}
			
			
			
			
			
			//CHANGE THE GAME TO THE INTERIM STATE
			function changeState(){
				var x = createCORSRequest('GET',"http://localhost:7777/toptrumps/changeState");
				x.onload = function(e){
						var details = x.response;
						var s = details.split(/[\r\n]+/);
						var activePlayer = s[1];
						var name = s[2];
						var comb = s[3];
						var lew = s[4];
						var agil = s[5];
						var lun = s[6];
						var iq = s[7];
						// MODIFY HUMAN PLAYERS CARD
						let card = document.getElementById('clmF');
						let cat_one = clmF.querySelector('h3');
						let cat_two = clmF.querySelector('h4');
						cat_one.innerHTML = name;
						cat_two.innerHTML = comb + "</br>" + lew + "</br>" + agil + "</br>" + lun + "</br>" + iq;
						ifHuman(activePlayer);
						// MODIFY PLAYER DISPLAY
						let player = document.querySelector('.player');
						let cat_three = player.querySelector('.pDisp'); 
						cat_three.innerHTML = "Good news everyone! It's " + activePlayer + "'s turn!";
						};
				x.send();
				//HIDE THE DRAW DISPLAY
				hideDD();
				//HIDE THE COMMUNAL PILE
				hideCP();
				//UPDATE PLAYERS HANDS
				getAllHands();
				//HIDE THE CONTINUE BUTTON
				hideCNT();
				//UPDATE THE AI CARDS
				getAICards();
				//HIDE AI CARDS
				hideAICards();
				//SHOW THE PLAY ROUND BUTTON
				showPR();
				//UPDATE DATABASE STATS
				database();
				//CHECK IF OVER
				isItOver();
			}
			
			
			
			
			//AI PLAY ROUND FUNCT
			function playRound(){
			//MAKE CORS RE
			var pR = createCORSRequest('GET', "http://localhost:7777/toptrumps/playRound");
			//CHECK CORS VALID
			if (!pR) {alert("CORS not supported");}
			pR.send();
			//AFTER ROUND PLAYED...
			//SHOW THE ACTIVE PLAYER 
			getWhoWon();
			//SHOW PLAYER CURRENT CARD
			getDraw();
			//UPDATE HANDS
			getAllHands();
			//CHECK THE COMMUNAL PILE
			getCardsInPlay();
			//CHECK FOR AN OVERALL WINNER
			isItOver();
			//SHOW THE AI CARDS
			showAICards();
			//UPDATE DATABASE STATS
			database();
			} 
			
			
			
			
			
			
			//PEEK SCRIPT - DETAILS OF CURRENT CARD
			//DECLARE FUNC
			function peek(){
			//MAKE CORS RE
			var pk = createCORSRequest('GET', "http://localhost:7777/toptrumps/peek");
			//CHECK CORS VALID
			if (!pk) {alert("CORS not supported");} 
			//WHEN PEEK REQUEST LOADS, THE RESPONSE WILL BE THE CARD DEETS. 
			//UPDATES HTML
			pk.onload = function(e){
				var responseText = pk.response; //CARD DEETS
				var s = responseText.split(/[\r\n]+/);
				var name = s[1];
				var comb = s[2];
				var lew = s[3];
				var agil = s[4];
				var lun = s[5];
				var iq = s[6];
				let card = document.getElementById('clmF');
				let cat_one = clmF.querySelector('h3');
				let cat_two = clmF.querySelector('h4');
				cat_one.innerHTML = name;
				cat_two.innerHTML = comb + "</br>" + lew + "</br>" + agil + "</br>" + lun + "</br>" + iq;
			};
			//SEND THE CORS
			pk.send();
			}
			
			
			
			
			
			
			
			//SHOW ALL THE AI CARDS AT THE END OF EACH ROUND
			function getAICards(){
			//CORS REQUEST
			var x = createCORSRequest ('GET', "http://localhost:7777/toptrumps/getAICards"); 
			//UPDATES HTML
			x.onload = function(e){
				var responseText = x.response; //CARD DEETS
				var s = responseText.split(/[\r\n]+/);
				var pName1 = s[0];
				var cName1 = s[2]
				var comb1 = s[3];
				var lew1 = s[4];
				var agil1 = s[5];
				var lun1 = s[6];
				var iq1 = s[7];
				//
				var pName2 = s[9];
				var cName2 = s[11]
				var comb2 = s[12];
				var lew2 = s[13];
				var agil2 = s[14];
				var lun2 = s[15];
				var iq2 = s[16];
				//
				var pName3 = s[18];
				var cName3 = s[20]
				var comb3 = s[21];
				var lew3 = s[22];
				var agil3 = s[23];
				var lun3 = s[24];
				var iq3 = s[25];
				//
				var pName4 = s[27];
				var cName4 = s[29]
				var comb4 = s[30];
				var lew4 = s[31];
				var agil4 = s[32];
				var lun4 = s[33];
				var iq4 = s[34];
				//
				let c1 = document.getElementById('c1');
				let c1_h = c1.querySelector('h3');
				let c1_h2 = c1.querySelector('h2');
				let c1_p = c1.querySelector('h4');
				c1_h.innerHTML = pName1;
				c1_h2.innerHTML = cName1;
				c1_p.innerHTML = comb1 + "</br>" + lew1 + "</br>" + agil1 + "</br>" + lun1 + "</br>" + iq1;
			//
				let c2 = document.getElementById('c2');
				let c2_h = c2.querySelector('h3');
				let c2_h2 = c2.querySelector('h2');
				let c2_p = c2.querySelector('h4');
				c2_h.innerHTML = pName2;
				c2_h2.innerHTML = cName2;
				c2_p.innerHTML = comb2 + "</br>" + lew2 + "</br>" + agil2 + "</br>" + lun2 + "</br>" + iq2;
			//
				let c3 = document.getElementById('c3');
				let c3_h = c3.querySelector('h3');
				let c3_h2 = c3.querySelector('h2');
				let c3_p = c3.querySelector('h4');
				c3_h.innerHTML = pName3;
				c3_h2.innerHTML = cName3;
				c3_p.innerHTML = comb3 + "</br>" + lew3 + "</br>" + agil3 + "</br>" + lun3 + "</br>" + iq3;
			//
				let c4 = document.getElementById('c4');
				let c4_h = c4.querySelector('h3');
				let c4_h2 = c4.querySelector('h2');
				let c4_p = c4.querySelector('h4');
				c4_h.innerHTML = pName4;
				c4_h2.innerHTML = cName4;
				c4_p.innerHTML = comb4 + "</br>" + lew4 + "</br>" + agil4 + "</br>" + lun4 + "</br>" + iq4;
			};
			//SEND THE CORS
			x.send();
			}
			
			
			
			
			
			//SHOW THE DRAW DISPLAY
			function showDD(){
			let showDrawDisplay = document.querySelector('.drawDisplay');
			let show = showDrawDisplay.querySelector('.display');
			show.hidden = false;
			}
			
				
			
			
			//HIDE THE DRAW DISPLAY
			function hideDD(){
			let showDrawDisplay = document.querySelector('.drawDisplay');
			let show = showDrawDisplay.querySelector('.display');
			show.hidden = true;
			}
			
			
			
			//SHOW THE COMMUNAL PILE
			function showCP(){
			let showCommunalPile = document.querySelector('.csInP');
			let show = showCommunalPile.querySelector('.display');
			show.hidden = false;
			}
									
			
											
			//HIDE THE COMMUNAL PILE
			function hideCP(){								
			let showCommunalPile = document.querySelector('.csInP');
			let show = showCommunalPile.querySelector('.display');
			show.hidden = true;
			}
			
			
			
			
			//CHECK FOR A DRAW
			//GETDRAW
			function getDraw(){
				var x = createCORSRequest('GET', "http://localhost:7777/toptrumps/getDraw");
				x.onload = function(e){
					var rsp = x.response;
					if (rsp == "true"){
					//SHOW THE COMMUNAL PILE
					rsp = "DANGIT! There's a draw!";
					showCP();
					//SHOW WHO DREW
					showDD();
					let showDrawDisplay = document.querySelector('.drawDisplay');
					let show = showDrawDisplay.querySelector('.display');
					show.innerHTML = rsp;
					}
				};
				x.send();
			}
			
			
			
			
			//HIDE ALL FOR THE END GAME SCREEN - EXCEPT THE PLAYER DISPLAY TO SHOW WINNER
			function hideAll(){
				var x = document.querySelector('.body-content');
				x.hidden = true;
				var y = document.querySelector('.categories');
				var z = document.querySelector('.csInP');
				var a = document.querySelector('.playersHands');
				var b = document.querySelector('.btn');
				x.hidden = "true";
				y.hidden = "true";
				z.hidden = "true";
				a.hidden = "true";
				b.hidden = "true";
			}
			
			
			
			//SAY WHO WON THE OVERALL GAME
			function showWinner(){
				//MAKE CORS REQUEST TO GET LAST WINNER'S NAME
				var wW = createCORSRequest('GET', "http://localhost:7777/toptrumps/getWhoWon");
				//CHECK CORS 
				if (!wW) {alert("CORS not supported");}
				//ON LOAD CREATE
				//A VAR USING
				//THE RESPONSE TEXT 
				//IE THE WINNER OF THE PREVIOUS ROUND'S NAME AS A STRING
				wW.onload = function(e){
					gameWinner = wW.response;
					let player = document.querySelector('.player');
					let cat_one = player.querySelector('h3'); 
					cat_one.innerHTML = "Congratulations " + whoWon +" you won the game!";
					};
			}
			
			
			
			//HIDE KILL SWITCH
			function hideKillSwitch () {
					document.getElementById("killSwitch").style.display = "none";
					}
			
			//SHOW KILL SWITCH
			function showKillSwitch () {
					document.getElementById("killSwitch").style.display = "block";
					}
			
			
			
			//KILL GAME
			function killGame(){
					var y = createCORSRequest('GET',"http://localhost:7777/toptrumps/killSwitch");
					//CHECK IF CORS VALID
					if (!y) {
							alert("CORS not supported");
							}
					y.onload = function(e){alert("sent");};
					//SEND
					y.send();
				   }
			
			
			//HAS THE GAME ENDED???
			//WE NEED TO FINISH THIS
			//DISPLAY WHO WON
			//HIDE EVERYTHING
			//BUTTON TO PLAY A NEW GAME
			//DECLARE
			function isItOver(){
				var iIO = createCORSRequest('GET', "http://localhost:7777/toptrumps/isItOver");
			//CHECK IF CORS VALID
				if (!iIO) {alert("CORS not supported");}
			//THE RETURNED REQUEST TEXT WILL SAY IF IT'S OVER
				iIO.onload = function(e){
					var responseText = iIO.response;
					if (responseText != ""){
						//HIDE EVERYTHING
						hideAll();
						//SAVE DATABASE STATS
						recordStats();
						//SHOW WINNER DIV
						showWinner();
						//SHOW END GAME BUTTON
						showKillSwitch();
						};
					}; 
			//SEND
			iIO.send();
			}
			
			
			
			
			
			
			//CHOOSE CATEGORY ALSO PLAYS A ROUND!!!
			//DECLARE
			function chooseCat(choice){
			//CORS
			var cC = createCORSRequest('GET', "http://localhost:7777/toptrumps/chooseCat?choice="+choice);
			//CHECK CORS VALID
			if (!cC) {alert("CORS not supported");}
			cC.send();
			//AFTER ROUND PLAYED
			//WHOSE CARD WON IS DISPLAYED 
			getWhoWon();
			//CHECK FOR A DRAW
			getDraw();
			//UPDATE PLAYERS HANDS
			getAllHands();
			//SHOW AI CARDS
			showAICards();
			//CHECK FOR A WINNER
			isItOver();	
			}
			
			//OPENS CONNECTION WITH DATABASE AND COLLECTS THE STATS TO BE STORED
			function database(){
			var data = createCORSRequest('GET', "http://localhost:7777/toptrumps/database");
			data.send();
			}
			
			//SAVE THE STATS TO THE DATABASE AND CLOSES THE CONNECTION
			function recordStats(){
			var recStats = createCORSRequest('GET', "http://localhost:7777/toptrumps/recordStats");
			recStats.send();
			}
			
			</script>
			
								
		</body>
		<div class="footer"></div><!--/footer-->
</html>