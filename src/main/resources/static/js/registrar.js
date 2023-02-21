// Call the dataTables jQuery plugin
$(document).ready(function() {
  // Lo dejo vacío
});

async function registrarUsuario(){
  let datos={};
  datos.nombre=document.getElementById('txtNombre').value;
  datos.apellidos=document.getElementById('txtApellidos').value;
  datos.email=document.getElementById('txtEmail').value;
  datos.password=document.getElementById('txtPassword').value;

  let repetirPassword=document.getElementById('txtRepetirPassword').value;
  if (repetirPassword != datos.password){
    alert(' las dos contraseñas no coinciden');
    return;
  }
  const request = await fetch('api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body:JSON.stringify(datos)
  });
  alert(' Usuario registrado');
  window.location.href='login.html';
}