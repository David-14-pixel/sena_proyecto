function encontrarEmpleado(e){
    let valor = e.target.value;
    let informacion = document.querySelector("#informacion_empleado");
    let nombre_empleado = document.querySelector("#nombre_empleado");
    let fecha_nacimiento_empleado = document.querySelector("#fecha_nacimiento_empleado");
    let edad_empleado = document.querySelector("#edad_empleado");
    let turno_empleado = document.querySelector("#turno_empleado");
    let botones = document.querySelector("#botones");
    fetch('empleados/encontrar/'+valor)
    .then(res => res.json())
    .then(em => {
            informacion.style.display = 'block';
            nombre_empleado.innerHTML = em.nombre;
            fecha_nacimiento_empleado.innerHTML = em.fecha_nacimiento;
            edad_empleado.innerHTML = em.edad;
            turno_empleado.innerHTML = em.turno.horarios;
            botones.innerHTML = `
            <a href="/empleados/${em.id}/editar" class="btn btn-primary btn-sm">Editar</a>
            <a href="/empleados/${em.id}/eliminar" class="btn btn-danger btn-sm">Eliminar</a>
            <a href="/nominas/${em.id}/liquidar" class="btn btn-primary btn-sm">Liquidar nomina</a>
            <a href="/nominas/${em.id}/detalle" class="btn btn-primary btn-sm">Movimientos de nomina</a>
            <br>
            <br>
            <a href="/nominas/${em.id}/export/pdf" class="btn btn-danger btn-sm">Exportar PDF</a>
            <a href="/nominas/${em.id}/export/excel" class="btn btn-success btn-sm">Exportar Excel</a>
            `



    })
    .catch(em=>{
        informacion.style.display = 'none';
    })
}