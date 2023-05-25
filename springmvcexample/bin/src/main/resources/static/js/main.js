function clock(){
	let element = documen.createElement("h3");
	element.setAttribute("id","horavalor");
	document.body.appendChild(element)
	
	setInterval (()=>{
		let hora = new Date().toLocaleTimeString();
		document.getElementById("horavalor").innerHTML = hora
	},1000);
}