var app = angular.module("rcbs", []);


var u = "/RmbDistrict/";
var url = "/RmbDistrict/";
var adminurl = "/RmbDistrict/manageRmbDistrict/";


/*var u = "/";
var url = "/";
var adminurl = "/manageRmbDistrict/";*/

//Allow numbers only with one decimal point derective

app.directive('allowDecimalNumbers', function () {  
    return {  
        restrict: 'A',  
        link: function (scope, elm, attrs, ctrl) {  
            elm.on('keydown', function (event) {  
                var $input = $(this);  
                var value = $input.val();  
                value = value.replace(/[^0-9\.]/g, '')  
                var findsDot = new RegExp(/\./g)  
                var containsDot = value.match(findsDot)  
                if (containsDot != null && ([46, 110, 190].indexOf(event.which) > -1)) {  
                    event.preventDefault();  
                    return false;  
                }  
                $input.val(value);  
                if (event.which == 64 || event.which == 16) {  
                    // numbers  
                    return false;  
                } if ([8, 13, 27, 37, 38, 39, 40, 110].indexOf(event.which) > -1) {  
                    // backspace, enter, escape, arrows  
                    return true;  
                } else if (event.which >= 48 && event.which <= 57) {  
                    // numbers  
                    return true;  
                } else if (event.which >= 96 && event.which <= 105) {  
                    // numpad number  
                    return true;  
                } else if ([46, 110, 190].indexOf(event.which) > -1) {  
                    // dot and numpad dot  
                    return true;  
                } else {  
                    event.preventDefault();  
                    return false;  
                }  
            });  
        }  
    }  
});

// We already have a limitTo filter built-in to angular,
// let's make a startFrom filter
app.filter('startFrom', function() {
	return function(input, start) {
		start = +start; // parse to int
		return input.slice(start);
	}
});

app.directive('prettyp', function() {
	return function(scope, element, attrs) {
		$("[rel^='prettyPhoto']").prettyPhoto({
			deeplinking : false
		});
	}
})

app.directive('disallowSpaces', function() {
	  return {
	    restrict: 'A',

	    link: function($scope, $element) {
	      $element.bind('input', function() {
	        $(this).val($(this).val().replace(/ /g, ''));
	      });
	    }
	  };
	});

app.directive('capitalize', function()
		{
			return{
					require: 'ngModel',
					link: function(scope, element, attrs, modelCtrl)
					{
						var capitalize = function(inputValue)
						{
							if (inputValue == undefined) inputValue = '';
							var capitalized = inputValue.toUpperCase();
							if (capitalized !== inputValue)
							{
								modelCtrl.$setViewValue(capitalized);
								modelCtrl.$render();
							}
							return capitalized;
						}
						modelCtrl.$parsers.push(capitalize);
						capitalize(scope[attrs.ngModel]); // capitalize initial value
					}
				};
		});

/*For Convert Value of Textbox into Uppercase End*/


/*For Convert First Character of Textbox into Uppercase Start*/

app.directive('capitalizeFirst', function(uppercaseFilter, $parse) {
	return {
		require: 'ngModel', link: function(scope, element, attrs, modelCtrl) {
			var capitalize = function(inputValue) {
				var capitalized = inputValue.charAt(0).toUpperCase() + inputValue.substring(1);
				if(capitalized !== inputValue) {
					modelCtrl.$setViewValue(capitalized);
					modelCtrl.$render();
				}
				return capitalized;
			}
			var model = $parse(attrs.ngModel);
			modelCtrl.$parsers.push(capitalize);
			capitalize(model(scope));
		}
	};
});



app.filter('limitHtml', function() {
    return function(text, limit) {

        var changedString = String(text).replace(/<[^>]+>/gm, '');
        var length = changedString.length;

        return changedString.length > limit ? changedString.substr(0, limit - 1) : changedString; 
    }
})

/* For Print data with html tag start */
app.filter('to_trusted', ['$sce', function($sce)
        {
			return function(text)
			{
				return $sce.trustAsHtml(text);
			};
		}]);
/* For Print data with html tag end */

angular.module('ng').filter('cut', function () {
       return function (value, wordwise, max, tail) {
           if (!value) return '';

           max = parseInt(max, 10);
           if (!max) return value;
           if (value.length <= max) return value;

           value = value.substr(0, max);
           if (wordwise) {
               var lastspace = value.lastIndexOf(' ');
               if (lastspace !== -1) {
                 //Also remove . and , so its gives a cleaner result.
                 if (value.charAt(lastspace-1) === '.' || value.charAt(lastspace-1) === ',') {
                   lastspace = lastspace - 1;
                 }
                 value = value.substr(0, lastspace);
               }
           }

           return value + (tail || ' …');
       };
   });