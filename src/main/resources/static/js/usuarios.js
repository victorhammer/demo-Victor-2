 $(document).ready(function() {
   cargaUsuarios();
   $('#usuarios').DataTable();
   acrualizarEmailDelUsuario();
 });

function acrualizarEmailDelUsuario(){
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

 async function cargaUsuarios(){
   const request = await fetch('api/usuarios', {
     method: 'GET',
     headers: getHeaders()
   });
   const usuarios = await request.json();
   let listadoHTML='';
   for (let usuario of usuarios){

     let botonEliminar ='<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'

     let telefonoTexto=usuario.telefono == null ? '-':usuario.telefono;
     let usuarioHTML= '<tr><td>'+usuario.id+
                      '<td>'+usuario.nombre+
                      '</td><td>'+usuario.email+
                      '</td><td>'+usuario.telefono+
                      '</td><td>'+botonEliminar+'</td><tr>';
     listadoHTML+=usuarioHTML;
   }
   console.log(usuarios);

   document.querySelector('#usuarios tbody').outerHTML=listadoHTML;
 }
function getHeaders(){
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization':localStorage.token
    };
}
 async function eliminarUsuario(id){
     if (!confirm('¿Desea borrar el usuario?')){
         return;
     }
     alert(' voy a eliminar el usuario '+id);
     const request = await fetch('api/usuarios/'+id, {
         method: 'DELETE',
         headers: getHeaders()
     });
     location.reload();
 }