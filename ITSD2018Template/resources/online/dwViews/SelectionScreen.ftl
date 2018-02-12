<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>

    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/)
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> -->

	</head>

	<style>
	@import url('https://fonts.googleapis.com/css?family=Bungee+Shade');


	body {
	background-color:coral;
	}



	#newGame{
	z-index: +2;
	font-family: 'Verdana', cursive;
	font-size: large;
	position: absolute;
	top: 50%;
		left: 50%;
		transform: translate(-58%, -35%);
    }

	#zoid{
	z-index: -1;
	position: absolute;
	top: 80%;
    left: 50%;
    transform: translate(-58%, -35%);
	}

	#buttons{
	width: 500px;
	font-size: 10px;
	font-family: 'Verdana', cursive;
	position: absolute;
	top: 50%;
    left: 55%;
    transform: translate(-58%, -35%);
	}

	#logo{
	height: 15%;
	position: absolute;
	top: 15%;
    left: 50%;
    transform: translate(-58%, -35%);
	z-index: +1;
	}

	.button {
	border-radius:50%;
    background-color: #6b5a7a;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: absolute;
    text-decoration: none;
    display: inline-block;
    font-family: 'Verdana', cursive;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    box-shadow: 5px 5px 2px black;
	}

	</style>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->

    	<!-- Should begin by asking new game or see stats-->

    	<!-- These forms contain the boxes that allow us to start a new game
		see stats or enter our play name and number of players. -->
    	<div class="container">

    	<div id="zoid">
    	<img src="https://raw.githubusercontent.com/aive/toptrump_images/master/zoid_piece.png" alt="Smiley face" style="width:300px;">
    	</div>

    	<!- THIS IS THE LOGO -->
    	<img id="logo" src="https://raw.githubusercontent.com/aive/toptrump_images/master/toptrumps_logo_red.png">


    	<div id="newGame" class="newGame">
		<form>
  		<input type="button" class="button" onclick="newGame()" value="New Game">
  		<input type="button" class="button" onclick="openStatsWindow()" value="See Scores">
  		<br>
		</form>
		</div>


    	<!-- This allows different player selections -->
		<div id="buttons" class="buttons" hidden=true>
		<form>
		<p style="color:white"> <font size="4px"> Choose your number of opponents!</p>
		<input id="oneAI" type="button" class="button" onclick="setAI(1)" value="1">
		<input id="twoAI" type="button" class="button" onclick="setAI(2)" value="2">
		<input id="threeAI" type="button" class="button" onclick="setAI(3)" value="3">
		<input id="fourAI" type="button" class="button" onclick="setAI(4)" value="4">
		</div>

    	</div>



<script type="text/javascript">

			// -----------------------------------------
			// I don't currently call anything on initialise...
			// -----------------------------------------
			// Method that is called on page load
				function initalize() {}
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------



			// Reusable method for creating a CORS request. Do not edit this.
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

		<!-- Beginning of REST methods -->
		<script type="text/javascript">

			//HIDE LOGO
			function hideLogo(){
				var xy = document.querySelector(".logo");
				xy.hidden = true;
			}


			//MAKE AI SELECTION BUTTONS VISIBLE
			function mkVis(){
				var x = document.getElementById("buttons");
    			x.hidden = false;
							}



			//HIDE AI SELECTION BUTTONS
			function hideAISelection(){
				var x = document.getElementById("buttons");
    			x.hidden = true;
							}


			//SHUFFLE AND DEAL
			//THIS IS CALLED BY 'NEW GAME'
			function shuf(){
			//CREATE CORS
			var shuf = createCORSRequest('GET', "http://localhost:7777/toptrumps/shuffleAndDeal");
			//SEND CORS
			shuf.send();
			}

			//HIDE THE NEW GAME / SEE STATS BUTTONS
			function hideNgSs(){
				var x = document.getElementById("newGame");
				x.hidden = true;
			}



			//NEW GAME
			//THIS IS CALLED NEW GAME BUT IT JUST MAKES THE
			//SELECT AI BUTTONS VISIBLE
			function newGame() {
			hideNgSs();
			mkVis();
			}


			//OPEN NEW WINDOW WHEN NUMBER OF ENEMIES SELECTED
			function openWindow() {window.location.replace("http://localhost:7777/toptrumps/game");}





			//SET NUMBER OF AI
			//THIS IS WHAT ACTUALLY STARTS THE GAME
			//
			//
			//
			//
			function setAI(ai){
				var setAI = createCORSRequest('GET', "http://localhost:7777/toptrumps/setAI?ai="+ai)
				// Check if CORS supported
				if (!setAI) {
  					alert("CORS not supported");
					}

				setAI.onload = function(e) {
						//var responseText = xhr.response; // the text of the response
						//alert(responseText); // lets produce an alert
				    openWindow();
				    //SHUFFLE AND DEAL THE DECK
				    //shuf();
				    //HIDE THE BUTTONS TO MAKE PAGE INACTIVE
				    hideAISelection();	
			};
			//CORS request setup so send it off!
			setAI.send();
			//OPENS A NEW WINDOW
			}





			//OPEN NEW WINDOW WHEN NEW SEE SCORES PRESSED
			function openStatsWindow() {window.location.replace("http://localhost:7777/toptrumps/stats");}



			//in javascript you can make html elements and insert them dynamically
			//lets say you have an empty div
			//you could have a 'render card' javascript method
			//you could create html objects
			//'create an html card'
			//set the id of the card to be this...
			//lookup an existing element by id
			//that returns the javascript equivalent of the thing
			//you can then add child


		</script>

		</body>
</html>
