<%@ attribute name="minesweeperBoard" required="false" rtexprvalue="true" type="org.springframework.samples.minesweeper.board.MinesweeperBoard"
 description="Minesweeper board to be rendered" %>
<canvas id="canvas" width="${minesweeperBoard.width}" height="${minesweeperBoard.height}"></canvas>
<img id="source" src="${minesweeperBoard.background}" style="display:none">
<img id="FLAG" src="resources/images/flag.png" style="display:none">
<img id="UNPRESSED" src="resources/images/unpressed.png" style="display:none">



<script>
var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var image = document.getElementById('source');

ctx.drawImage(image, 0, 0, ${minesweeperBoard.width}, ${minesweeperBoard.height});
</script>


<!--  
cell.covered - cell.flagged - cell.hasMine - cell.minesAround
<img id="a" src="resources/images/sinDescubrirYSinBandera.png" style="display:none">
<img id="b" src="resources/images/.png" style="display:none">
<img id="c" src="resources/images/.png" style="display:none">
<img id="d" src="resources/images/.png" style="display:none">
<img id="e" src="resources/images/.png" style="display:none">
<img id="f" src="resources/images/.png" style="display:none">
<img id="g" src="resources/images/.png" style="display:none">
<img id="h" src="resources/images/.png" style="display:none">
<img id="i" src="resources/images/.png" style="display:none">
<img id="j" src="resources/images/.png" style="display:none">
<img id="k" src="resources/images/.png" style="display:none">
<img id="l" src="resources/images/.png" style="display:none">




 -->