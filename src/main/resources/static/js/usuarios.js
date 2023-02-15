// Call the dataTables jQuery plugin
$(document).ready(function() {
  alert("Esto es una prueba");
  $('#usuarios').DataTable();
});

function cargaUsuarios(){

const request = await fetch('usuario/234',{
    method: 'GET',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        }
    });
    const usuarios = await request.json();

    console.log(usuarios);

}