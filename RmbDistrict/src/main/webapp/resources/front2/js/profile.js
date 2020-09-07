    $(document).ready(function () {
        //Initialize tooltips
        $('.nav-tabs > li a[title]').tooltip();

        $.validator.addMethod(
          "cusDate",
          function(value, element) {
              // put your own logic here, this is just a (crappy) example
              return value.match(/^\d\d?\/\d\d?\/\d\d\d\d$/);
             },
               "Please enter a date in the format dd/mm/yyyy."
           );


          $("#primaryPhone").mask("(999)-999-9999");
          $("#workPhone").mask("(999)-999-9999");

         var $validator = $("#bankForm").validate({
              rules: {
                firstName: {
                  required: true,
                  minlength: 3
                },
                lastName: {
                  required: true,
                  minlength: 3
                },
                address1: {
                  required: true,
                  minlength: 10
                },
                monthlyPayment: {
                  required: true,
                  digits: true
                },
                years:{
                  required: true,
                  digits: true
                },
                months: {
                  required: true,
                  digits: true
                },
                expsYears: {
                  required: true,
                  digits: true
                },
                expsMonths: {
                  required: true,
                  digits: true
                },
                annualIncome: {
                  required: true,
                  digits: true
                },
                otherIncome: {
                  required: true,
                  digits: true
                },
                loanAmount: {
                  required: true,
                  digits: true
                },
                estimation: {
                  required: true,
                  digits: true
                },
                existingBalance: {
                  required: true,
                  digits: true
                },
                pinCode:{
                  required: true,
                  digits: true
                },
                prvsPinCode:{
                  required: true,
                  digits: true
                },
                issuedDate: {
                  required: true,
                  date: true,
                  cusDate : true
                },
                expirationDate: {
                  required: true,
                  date: true,
                  cusDate : true
                },
                dateOfBirth: {
                  required: true,
                  date: true,
                  cusDate : true
                }

              } 
            });


         $('#rootwizard').bootstrapWizard({
              'tabClass': 'nav nav-tabs',
              'onNext': validateTab,
              'onTabClick': validateTab
            }); 
         function validateTab(tab, navigation, index, nextIndex){
                  if (nextIndex <= index){
                    return;
                  }
                  var bankForm = $("#bankForm")
                  var $valid = bankForm.valid();
                  if(!$valid) {
                    $validator.focusInvalid();
                    return false;
                  }
                    
                  if (nextIndex > index+1){
                   for (var i = index+1; i < nextIndex - index + 1; i++){
                     $('#rootwizard').bootstrapWizard('show', i);
                     $valid = bankForm.valid();
                     if(!$valid) {
                       $validator.focusInvalid();
                       return false;
                     }
                   }
                   return false;
                  }

               }

            });