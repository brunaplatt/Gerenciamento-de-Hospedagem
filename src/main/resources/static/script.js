$.get("/hospedes/buscarHospedes",function(data){
    $('[name=responsavel]').html('');
    $.each(data,function(index,row){
        $('[name=responsavel]').append('<option value=\"'+row.id+'\">'+row.nome+'</option>');
    });
});