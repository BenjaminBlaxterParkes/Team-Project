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

	<style>
	body{
	//width: 400px;
	background-color: coral;}
	
	.container{
	width: 400px;
	}
	
	p{
	margin-left: 100px;
	margin-right: 100px;
	font-size:20px;
	}
	
	button{
	margin-left: 100px;
	margin-right: 100px;
	}
	</style>

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">
			
			<header><strong>Here's the game statistics chump! Bite my shiny metal ass!</strong></header>
			
			<div id="stats" class="stats">
			<p>Test</p>
			</div>
			
			<div id="home" class="home">
			<button id="button" onclick="returnToHome()">Home</button>
			</div>
		
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
				getPreviousStats();	
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
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
			
			//FUNCTION TO RETRIEVE ALL PERSISTANT GAME STATISTICS FROM THE DATABASE
			function getPreviousStats(){
				var stats = createCORSRequest('GET', "http://localhost:7777/toptrumps/getPreviousStats");
				if (!stats) {alert("CORS not supported")};
			//THE RETURNED REQUEST TEXT WILL CONTAIN THE STATISTICS FROM THE DATABASE
				stats.onload = function(e){
					var statsText = stats.response;
					var s = statsText.split("\n");
					var gamesPlayed = s[0];
					var aiWins = s[1];
					var humanWins = s[2];
					var averageDraws = s[3];
					var largestRound = s[4];
					let statsDiv = document.querySelector('.stats');
					let statsP = statsDiv.querySelector('p');
					statsP.innerHTML = gamesPlayed + "<br>" + aiWins + "<br>" + humanWins + "<br>" + averageDraws + "<br>" + largestRound;
					};
				stats.send();
			}
		
		//RETURNS USER TO HOME SCREEN
		function returnToHome() {window.location.replace("http://localhost:7777/toptrumps");}
		
		</script>
		
		</body>
</html>