<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>

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

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->

    	<div class="container">
			<!-- Add your HTML Here -->
		</div>

				<!-- PR BUTTON -->
				<div id="btn" class="btn">
				<button id="pr" class="pr" onclick="playRound()">Play Round</button>
				<button id="cnt" class="cnt"> Continue? </button>
				</div>


				<!-- ACTIVE PLAYER DISPLAY -->
				<div id="player" class="player">
				<h3> </h3> <!-- This will be the player name -->
				<hr>
				</div>



				<!-- PLAYER CARD DISPLAY -->
				<div id="card" class="card">
				<h3>Players card</h3>
				<h1> </h1>
				<p> 1. Combat: </br>
					2. Lewdness: </br>
					3. Agility: </br>
					4. Lunacy: </br>
					5. IQ: </p>
				<hr>
				</div>



				<!-- AI CARDS -->
				<div id="aiCards" class="aiCards">

				<!-- HAL CARD DISPLAY -->
				<div id="hCard" class="hCard">
				<h3>HAL 9000 card</h3>
				<h1> </h1>
				<p> 1. Combat: </br>
					2. Lewdness: </br>
					3. Agility: </br>
					4. Lunacy: </br>
					5. IQ: </p>
				<hr>
				</div>

				<!-- CORTANA CARD DISPLAY -->
				<div id="cCard" class="cCard">
				<h3>Cortana's card</h3>
				<h1> </h1>
				<p> 1. Combat: </br>
					2. Lewdness: </br>
					3. Agility: </br>
					4. Lunacy: </br>
					5. IQ: </p>
				<hr>
				</div>

				<!-- GLADoS CARD DISPLAY -->
				<div id="gCard" class="gCard">
				<h3>GLADoS' card</h3>
				<h1> </h1>
				<p> 1. Combat: </br>
					2. Lewdness: </br>
					3. Agility: </br>
					4. Lunacy: </br>
					5. IQ: </p>
				<hr>
				</div>

				<!-- MARVIN CARD DISPLAY -->
				<div id="mCard" class="mCard">
				<h3>Marvin's card</h3>
				<h1> </h1>
				<p> 1. Combat: </br>
					2. Lewdness: </br>
					3. Agility: </br>
					4. Lunacy: </br>
					5. IQ: </p>
				<hr>
				</div>
				</div>


				<!-- SELECT CATEGORY -->
				<div id="categories" class="categories">
				<div></div>
				</div>



		<script type="text/javascript">

			// Method that is called on page load
			function initalize() {

				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------

				// For example, lets call our sample methods
				// I am only calling the new game method
				//helloJSONList();
				//helloWord("Student");


				//WHEN THE GAME STARTS WE
				//CHOOSE FIRST PLAYER
				chooseFirstPlayer();
				hide();
				hideAICards();


			}

			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------

		//HIDE THE BLOODY CNT BUTTON
		function hide(){
		let hide = document.querySelector('.btn');
		let cat_one = document.querySelector('.cnt');
		cat_one.hidden = true;
		}


		//HIDE AI CARDS
		function hideAICards(){
		let hide = document.querySelector('.aiCards');
		hide.hidden = true;
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



			//CHOOSE FIRST PLAYER
			//DECLARE FUNC
			function chooseFirstPlayer(){
			//CORS REQ
			var gP = createCORSRequest('GET', "http://localhost:7777/toptrumps/chooseFirstPlayer");
			if (!gP) {alert("CORS not supported");}
			gP.onload = function(e){
				var responseText = gP.response; //THIS SHOULD BE THE NAME OF THE FIRST PLAYER
				//alert(responseText);
				let player = document.querySelector('.player');
			let cat_one = player.querySelector('h3');
			cat_one.innerHTML = "Good news everyone " + responseText + " is first...";
			isHuman(responseText);
			};
			gP.send();
			//SHOW PLAYER CURRENT CARD
			peek();
			}




			//IS THE PLAYER HUMAN
			function isHuman(param1){
			//OLD TEST alert(param1+"has been passed");
			if(param1 === "player"){
			let categories = document.querySelector('.categories');
			let tmp = categories.querySelector('div');
			tmp.innerHTML = "<div><button onclick=\"chooseCat(1)\">Combat</button> <button onclick=\"chooseCat(2)\">Lewdness</button> <button onclick=\"chooseCat(3)\">Agility</button> <button onclick=\"chooseCat(4)\">Lunacy</button> <button onclick=\"chooseCat(5)\">Intelligence</button>	</div>";
			}
				else { let categories = document.querySelector('.categories');
				let tmp = categories.querySelector('div');
				tmp.innerHTML = "<div> </div>";}
			}





			//WHOSE TURN
			//GET THE ACTIVE PLAYER NAME
			function whoseTurn(){
			//MAKE CORS
			var wT = createCORSRequest('GET', "http://localhost:7777/toptrumps/whoseTurn");
			//CHECK CORS
			if (!wT) {alert("CORS not supported");}
			//ON LOAD CREATE
			//A VAR USING
			//THE RESPONSE TEXT
			//WHICH SHOULD BE
			//THE ACTIVE PLAYER, IE THE WINNER OF THE PREVIOUS ROUND, NAME AS A STRING
			wT.onload = function(e){
			var whoseTurn = wT.response;
			//SAY WHO WON
			let player = document.querySelector('.player');
			let cat_one = player.querySelector('h3');
			cat_one.innerHTML = "Great, that chump " + whoseTurn +" won";
			//SHOW THE AI CARDS
			let showCards = document.querySelector('.aiCards');
			showCards.hidden = false;
			//HISE THE PLAY ROUND BUTTON
			let button = document.querySelector('.btn');
			let pr = button.querySelector('.pr');
			pr.hidden = true;
			//SHOW THE CONTINUE BUTTON
			let but = button.querySelector('.cnt');
			but.hidden = false;
			//IF IT'S THE PLAYER SHOW THE BUTTONS
			isHuman(whoseTurn);
			}
			wT.send();

			}



			//AI PLAY ROUND FUNCT
			function playRound(){
			//MAKE CORS RE
			var pR = createCORSRequest('GET', "http://localhost:7777/toptrumps/playRound");
			//CHECK CORS VALID
			if (!pR) {alert("CORS not supported");}
			//OLD TESTalert("A Round has been played!")
			pR.send();
			//AFTER ROUND PLAYED
			//SHOW THE ACTIVE PLAYER
			whoseTurn();
			//CLEAR CARD DISPLAY
			let card = document.querySelector('.card');
				let cat_one = card.querySelector('h1');
				let cat_two = card.querySelector('p');
				cat_one.innerHTML = "";
				cat_two.innerHTML = "<p> 1. Combat: </br>2. Lewdness: </br>3. Agility: </br>4. Lunacy: </br>5. IQ: </p>";
			//SHOW PLAYER CURRENT CARD
			peek();
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
				var name = s[0];
				var comb = s[1];
				var lew = s[2];
				var agil = s[3];
				var lun = s[4];
				var iq = s[5];
				let card = document.querySelector('.card');
				let cat_one = card.querySelector('h1');
				let cat_two = card.querySelector('p');
				cat_one.innerHTML = name;
				cat_two.innerHTML = comb + "</br>" + lew + "</br>" + agil + "</br>" + lun + "</br>" + iq;
			};
			//SEND THE CORS
			pk.send();
			}




			//CHOOSE CATEGORY
			//DECLARE
			function chooseCat(choice){
			//I WANT CHOOSE CATEGORY TO INFLUENCE THE HTML

			//CORS
			var cC = createCORSRequest('GET', "http://localhost:7777/toptrumps/chooseCat?choice="+choice);
			//CHECK CORS VALID
			if (!cC) {alert("CORS not supported");}
			cC.send();
			//AFTER ROUND PLAYED
			//WHOSE CARD IS DISPLAYED
			whoseTurn();
			//CLEAR CARD DISPLAY
			let card = document.querySelector('.card');
				let cat_one = card.querySelector('h1');
				let cat_two = card.querySelector('p');
				cat_one.innerHTML = "";
				cat_two.innerHTML = "<p> 1. Combat: </br>2. Lewdness: </br>3. Agility: </br>4. Lunacy: </br>5. IQ: </p>";
			//SHOW PLAYER CURRENT CARD
			peek();

			}
			</script>


		</body>
</html>
