<!doctype html>
<html>
<head>
	<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
	
	<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery-ui.structure.min.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery-ui.theme.min.css" type="text/css" />
<style>
	body{ background-color: ivory; }
	canvas{border:1px solid red;}
</style>

<script>
$(function(){
	var spinner = $( "#spinner" ).spinner({
		step: 0.01
	});

	var canvas=document.getElementById("canvas");
	var ctx=canvas.getContext("2d");
	
	var value = spinner.spinner( "value" );
	var origin = 1;
	
	setInterval(function() {
		var value = spinner.spinner( "value" );
		drawButton();
	}, 10);
	
	function drawButton() {
		var firstBound = origin+0.5+0;
		var secondBound = origin+0.5+spinner.spinner( "value" );
		
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		ctx.save();
		ctx.beginPath();
		ctx.moveTo(100, 100);
		ctx.arc(100, 100, 50, Math.min(firstBound, secondBound)*Math.PI, Math.max(firstBound, secondBound)*Math.PI, false);
		ctx.closePath();
		ctx.fillStyle = 'green';
		ctx.fill();
		
		ctx.beginPath();
		ctx.arc(100, 100, 37, 0, 2 * Math.PI, false);
		ctx.fillStyle = 'yellow';
		ctx.fill();
		ctx.lineWidth = 1;
		ctx.strokeStyle = 'black';
		ctx.stroke();
	}
	
}); // end $(function(){});
</script>

</head>

<body>
	<canvas id="canvas" width=400 height=300></canvas>
	<input id="spinner" name="value" />
</body>
</html>