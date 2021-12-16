<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->


<minesweeper:layout pageName="tutorial">

	
	



	<h1>Tutorial Minesweeper</h1>
	</br>
	<h2>Goal:</h2>
	<p>The goal of the game is to discover all those squares on the board that do not contain mines.</p>
	</br>
	</br>
	<h2>Players:</h2>
	<p>Only one player can participate in this game.</p>
	</br>
	</br>
	<h2>Levels:</h2>
	<ul>
		<li>Beginner: the board consists of 8 rows and 8 columns and contains 10 mines.</li>
		<li>Medium: the board consists of 16 rows and 16 columns and contains 40 mines.</li>
		<li>Ace: the board consists of 16 rows and 30 columns and contains 99 mines.</li>
		<li>Custom: the player chooses the size of the board and the number of mines it will contain. 
		According to the following conditions:</li>
		<ul>
			<li>The minimum number of rows must be 8 and the maximum 24.</li>
			<li>The minimum number of columns must be 8 and the maximum 32.</li>
			<li>The minimum number of mines is 1 and the maximum number is 1/3 of the total number of cells.</li>
		</ul>
	</ul>
	</br>
	</br>
	<h2>Basic concepts:</h2>
	<p>At the beginning of the game, we will see a board with all the boxes unmarked. As we progress through the game,
    we will find the following elements:</p> 
		<ul>
			<li>The boxes in which a number appear, indicate the number of mines that are around them.</li>
			<li>Empty boxes.</li>
			<li>The flag, we will place it on those boxes in which we believe there is a mine.</li>
			<li>If when discovering a box in which a mine is found, the game will be over.</li>
		</ul>
	</br>
	</br>
	<h2>How to play:</h2>
	<p>As explained in the goal, we must clear the boxes without detonating any mines.
    To do this, you have to look at the boxes that contain a number and place the flags where it is believed that there may be a mine, 
    so as not to discover those boxes. Once we have discovered all those boxes
    that do not contain mines, the game is over and the player has won. By the time it is
    uncover a space with a mine, the game will be over and the player will have lost.</p>
</minesweeper:layout>