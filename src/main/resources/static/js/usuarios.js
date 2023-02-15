$(document).ready(function() {
  alert("Esto es una prueba");
  $('#usuarios').DataTable();
});

function cargaUsuarios(){

const request = await fetch('api/usuarios',{
    method: 'GET',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        }
    });
    const usuarios = await request.json();
      let listadoHTML='';
      for (let usuario of usuarios){
        let usuarioHTML= '<tr><td>'+usuario.id+
                         '<td>'+usuario.nombre+
                         '</td><td>'+usuario.email+
                         '</td><td>'+usuario.telefono+
                         '</td><td><a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td><tr>';
        listadoHTML+=usuarioHTML;
      }
      console.log(usuarios);

      document.querySelector('#usuarios tbody').outerHTML=listadoHTML;
}