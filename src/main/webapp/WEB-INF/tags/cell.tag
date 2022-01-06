<%@ attribute name="size" required="true" rtexprvalue="true" 
 description="Size of the cell to show" %>
 <%@ attribute name="cell" required="true" rtexprvalue="true" type="org.springframework.samples.minesweeper.board.Cell"
 description="Cell to be rendered" %>
 <script>
 var canvas = document.getElementById("canvas");
 var ctx = canvas.getContext("2d");
 var image = document.getElementById('${cell.type}');
 ctx.drawImage(image,${cell.getPositionYInPixels(size)},${cell.getPositionXInPixels(size)},${size},${size});
 </script>