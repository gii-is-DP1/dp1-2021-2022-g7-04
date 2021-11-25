<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="buscaminas" tagdir="/WEB-INF/tags" %>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->


<buscaminas:layout pageName="tutorial">
	<h1>Tutorial</h1>
	</br>
	<h2>Objetivo:</h2>
	<p>El objetivo es ir descubriendo todas aquellas casillas del tablero que no contengan minas.</p>
	</br>
	</br>
	<h2>Jugadores:</h2>
	<p>En este juego solo participa un jugador.</p>
	</br>
	</br>
	<h2>Niveles:</h2>
	<ul>
		<li>Principiante: el tablero consta de 8 filas y 8 columnas. Contiene 10 minas.</li>
		<li>Intermedio: el tablero consta de 16 filas y 16 columnas. Contiene 40 minas.</li>
		<li>Experto: el tablero consta de 16 filas y 30 columnas. Contiene 99 minas.</li>
		<li>Personalizado: el jugador elige el tamaño del tablero y el número de minas que contendrá. 
		Según las siguientes condiciones:</li>
		<ul>
			<li>El número mínimo de filas debe ser 8.</li>
			<li>El número mínimo de columnas debe ser 8.</li>
			<li>El número máximo es 99.</li>
		</ul>
	</ul>
	</br>
	</br>
	<h2>Conceptos básicos:</h2>
	<p>Al comenzar la partida, veremos un tablero con todas las casillas sin marcar, conforme vamos jugando
	 nos iremos encontrando los siguientes elementos:</p> 
		<ul>
			<li>Las casillas en las que aparecen un número, indican el número de minas que hay alrededor de ellas.</li>
			<li>Casillas vacías.</li>
			<li>El flag, lo colocaremos sobre aquellas casillas en las que creemos que hay una mina.</li>
			<li>La mina, al descubrir una casilla en la que se encuentre una mina el juego habrá terminado.</li>
		</ul>
	</br>
	</br>
	<h2>¿Cómo se juega?</h2>
	<p>Como se explica en el objetivo, debemos ir despejando las casillas sin que detone ninguna mina.
	Para ello hay que ir fijandose en las casillas que contienen un número y colocar los flags donde pensemos 
	que haya unas mina, para no descubrir esas casillas. Una vez hayamos descubierto todas aquellas casillas
	 que no contienen minas, la partida habra finalizado y el jugador habrá ganado. En el momento en que se 
	 descubra una casilla con mina, la partida habra finalizado y el jugador habrá perdido.</p>
</buscaminas:layout>
