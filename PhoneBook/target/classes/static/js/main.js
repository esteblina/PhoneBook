
$(document).ready(function () {

    (function ($) {

        $('#filter').keyup(function () {

            var rex = new RegExp($(this).val(), 'i');
            $('tr.search').hide();
            $('tr.search').filter(function () {
                return rex.test($(this).find('.searchable').text());
            }).show();

        })

    }(jQuery));


$('#myModal').on('show.bs.modal', function(e) {

    var $modal = $(this),
        id = e.relatedTarget.id;

    $.ajax({
        url: '/phone/find?id=' + id,
        method: 'GET'
    }).success(function(response) {
    	var phone =JSON.parse(response);
        // Populate the form fields with the data returned from server
        $modal.find('#lastname').val(phone.lastname);
    	$modal.find('#firstname').val(phone.firstname);
    	$modal.find('#patronymic').val(phone.patronymic);
     	$modal.find('#mobilephone').val(phone.mobilephone);
     	$modal.find('#homephone').val(phone.homephone);
     	$modal.find('#address').val(phone.address);
     	$modal.find('#email').val(phone.email);
     	$modal.find('#id').val(phone.id);
     	$modal.find('#user_id').val(phone.user_id);
    });
    
    $("#edit").click(function() {
    	
    	var lastname=$modal.find('#lastname').val();
    	var firstname=$modal.find('#firstname').val();
    	var patronymic=$modal.find('#patronymic').val();
    	var mobilephone=$modal.find('#mobilephone').val();
    	var homephone=$modal.find('#homephone').val();
    	var address=$modal.find('#address').val();
    	var email=$modal.find('#email').val();
    	var id=$modal.find('#id').val();
    	if (lastname == '' || firstname == '' || patronymic == '' || mobilephone == '' || id == '' ) {
    		alert("Please fill all fields!");
    	} else if ((lastname.length)<4) {
    		alert("Last name should at least 4 character in length!");
    	}else if ((firstname.length)<4) {
    	    alert("First name should at least 4 character in length!");
    	} else if ((patronymic.length)<4) {
    		alert("Patronymic should at least 4 character in length!");
    	}else if (!/^(\+380)?((\(\d{2}\))|\d{2})?(\d{7}|(\d{3}-\d{2}-\d{2}))?$/.test(mobilephone)) {
    		alert("Mobilephone number invalid!");
    	}else if (homephone && !/^(\+380)?((\(\d{2}\))|\d{2})?(\d{7}|(\d{3}-\d{2}-\d{2}))?$/.test(homephone)) {
    		alert("Homehone number invalid!");
    	}else if (email && !/^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/.test(email)){
    	alert("e-mail invalid!");
    	} else {
    	 $('#new-phone').submit();
    	}
    	});
    });
$('#myModal2').on('show.bs.modal', function(e) {

    var $modal = $(this);
    
$("#add").click(function() {
	var lastname=$modal.find('#lastname').val();
	var firstname=$modal.find('#firstname').val();
	var patronymic=$modal.find('#patronymic').val();
	var mobilephone=$modal.find('#mobilephone').val();
	var homephone=$modal.find('#homephone').val();
	var address=$modal.find('#address').val();
	var email=$modal.find('#email').val();
	var id=$modal.find('#id').val();
	if (lastname == '' || firstname == '' || patronymic == '' || mobilephone == '' || id == '' ) {
		alert("Please fill all fields!");
	} else if ((lastname.length)<4) {
		alert("Last name should at least 4 character in length!");
	}else if ((firstname.length)<4) {
	    alert("First name should at least 4 character in length!");
	} else if ((patronymic.length)<4) {
		alert("Patronymic should at least 4 character in length!");
	}else if (!/^(\+380)?((\(\d{2}\))|\d{2})?(\d{7}|(\d{3}-\d{2}-\d{2}))?$/.test(mobilephone)) {
		alert("Mobilephone number invalid!");
	}else if (homephone && !/^(\+380)?((\(\d{2}\))|\d{2})?(\d{7}|(\d{3}-\d{2}-\d{2}))?$/.test(homephone)) {
		alert("Homehone number invalid!");
	}else if (email && !/^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/.test(email)){
	alert("e-mail invalid!");
	} else {
	 $('#save-phone').submit();
	}
	});
});
});



