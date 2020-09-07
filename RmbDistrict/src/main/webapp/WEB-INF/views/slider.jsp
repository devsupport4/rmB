
<style>
.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 100%;
  height:400px !important;
}
</style>
<section id="main-slider" class="no-margin">
	<div class="carousel slide" >
	
		<ol class="carousel-indicators active">
			<li data-target="#main-slider"  ng-repeat="item in activeSlider" data-slide-to="{{$index}}" ></li>

		</ol> 

	 	<div class="carousel-inner " >	
	 	
			<div class="item active"  ng-repeat="item in activeSlider" ng-if="$index == 0">
			<div  style="background-size: cover; height:350px !important;width:100%; background-repeat: no-repeat;" ng-click="redirectSlider(item.redirectUrl)">
				<a>	
					<img style="cursor:pointer;width:100%;" class="img-responsive hidden-xs hidden-sm" src="{{item.image}}" alt="{{item.sliderTitle}}" >
				</a> 
			</div>	
				

			</div>
				<div class="item "  ng-repeat="item in activeSlider" ng-if="$index != 0">
			<div  style="background-size: cover; height:350px !important;width:100%; background-repeat: no-repeat;" ng-click="redirectSlider(item.redirectUrl)">
				<a>	
					<img style="cursor:pointer;width:100%;" class="img-responsive hidden-xs hidden-sm" src="{{item.image}}" alt="{{item.sliderTitle}}" >
				</a> 
			</div>	
				

			</div>
		</div> 
		<a class="prev hidden-xs" href="#main-slider" data-slide="prev">
			<i class="fa fa-chevron-left"></i>
		</a>
		<a class="next hidden-xs" href="#main-slider" data-slide="next">
			<i class="fa fa-chevron-right"></i>
		</a>
	
	</div>
	
		
	
</section> 