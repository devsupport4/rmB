/***************** Dropdown Search & Select Strat ***********************/
$(document).ready(function() {
var x = new SlimSelect({
select: '#demo'
});
});



$(document).ready(function() {
var x = new SlimSelect({
select: '#demo1'
});
});

/***************** Dropdown Search & Select End ***********************/




/* HTML CKEDITOR Sc */

CKEDITOR.replace('editor1', {
height: 150
});

CKEDITOR.replace('editor2', {
height: 150
});

/* HTML CKEDITOR Sc */



/**************** Date & Time Picker **********************/

$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#fromdatetimepicker").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});


$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#fromdatetimepickeredit").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});



$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#todatetimepicker").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});



$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#todatetimepickeredit").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});




$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#fromdatetimepicker1").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});



$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#fromdatetimepicker2").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});



$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#fromdatetimepicker3").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});



$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#addactivitydatetimepicker").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});







$(document).ready(function () {
// create DateTimePicker from input HTML element
$("#meetingdate").kendoDateTimePicker({
value: new Date(),
dateInput: true
});
});




/********************datepicker Only*************************/
$(document).ready(function() {
// create DatePicker from input HTML element
$("#filterfromdatepicker").kendoDatePicker();

$("#monthpicker").kendoDatePicker({
// defines the start view
start: "year",

// defines when the calendar should return date
depth: "year",

// display month and year in the input
format: "MMMM yyyy",

// specifies that DateInput is used for masking the input element
dateInput: true
});
});



$(document).ready(function() {
// create DatePicker from input HTML element
$("#filtertodatepicker").kendoDatePicker();

$("#monthpicker").kendoDatePicker({
// defines the start view
start: "year",

// defines when the calendar should return date
depth: "year",

// display month and year in the input
format: "MMMM yyyy",

// specifies that DateInput is used for masking the input element
dateInput: true
});
});


/********************datepicker Only*************************/




/**************** Date & Time Picker **********************/






/* Tool Tip popover  */

// Initialize tooltip component
$(function () {
$('[data-toggle="tooltip"]').tooltip()
})

// Initialize popover component
$(function () {
$('[data-toggle="popover"]').popover()
})

/* Tool Tip popover  */




$(document).ready(function() {
var x = new SlimSelect({
select: '#slocation',
});
});




/*Event Share with Users*/
$(document).ready(function() {
var x = new SlimSelect({
select: '#esusers',
});
});


/*Event Share with Groups*/
$(document).ready(function() {
var x = new SlimSelect({
select: '#esgroups',
});
});


