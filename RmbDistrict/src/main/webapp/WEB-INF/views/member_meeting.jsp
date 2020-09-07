<div class="modal-dialog" role="document">
<div class="modal-content">
<div  class="modal-header bg-gradient-primary">
<h5 class="modal-title text-white" id="exampleModalLabel">  Add New R2R Meetings </h5>
<button class="close text-white" type="button" data-dismiss="modal" aria-label="Close">
<i class="fa fa-times"></i>
</button>
</div>

<div class="modal-body">

<form class="">

<div class="form-group">
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<label> Met with <span class="red">*</span> </label>
<div class="team-members">
<div style="margin-bottom: 0px;" class="form-group  input-group">
<select  style="width: 100%;" id="demo1" name="metmemberid" id="metmemberid" ng-model="metmemberid" ng-change="setNewMemberId()"> >
<option value="">Select</option>
	<option ng-repeat="item in getmember" value="{{item.memberId}}" ng-hide="item.memberId == <%= session.getAttribute("loginid") %>">{{item.memberFirstName}} {{item.memberMiddleName}} {{item.memberLastName}}</option>
</select>	
</div>
</div>


</div>
</div>
</div>


<div class="form-group">
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<label> Meeting Date & Time  <span class="red">*</span>  </label>
<input id="todatetimepickeredit" title="todatetimepickeredit" ng-model="meetingdate" title="Meeting Date" placeholder="DD/MM/YYYY"  style="width: 100%;" />
</div>
</div>
</div>





<div class="form-group">
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<label> Location <span class="red">*</span> </label>
<input class="form-control input-lg"  name="location" id="location" ng-model="location" placeholder="Location* " type="text" />
</div>
</div>
</div>


<div class="form-group">
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<label> Topics of conversation <span class="red">*</span> </label>
<textarea placeholder="Enter topics..." name="topic" id="topic" ng-model="topic" class="form-control" rows="3"></textarea>
</div>
</div>
</div>


<div class="form-group">
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<!-- <a href="#"  ng-click="saveOnetoOne(flag='save')"  class="btn btn-add btn-block">
<span class="text">  Add Meeting </span>
</a>
 -->
 <button class="btn btn-primary" ng-click="saveOnetoOne(flag='save')" style="background-color:  #005daa;"><i class="fa fa-refresh fa-spin" ng-show="spin == 1"></i> Save</button>
</div>
</div>
</div>


</form>


</div>

</div>
</div>
