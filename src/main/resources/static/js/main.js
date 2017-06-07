$(document).ready(function () {
    $(".deleteUser").click(function () {
        var $self = $(this);
        var id = $self.data('id');
        $.ajax('/api/users/' + id, {
            type: 'DELETE'
        }).done(function (resp) {
            $self.parent().parent().remove();
            console.log(resp);
        }).fail(function (xhr, err) {
            console.log(err);
        });
        return false;
    });
    $(".deletePresentation").click(function () {
        var $self = $(this);
        var id = $self.data('id');
        $.ajax('/api/schedules/' + id, {
            type: 'DELETE'
        }).done(function (resp) {
            $self.parent().parent().remove();
            console.log(resp);
        }).fail(function (xhr, err) {
            console.log(err);
        });
        return false;
    });
});