function RaceController($scope, $http) {

    $http({
        url: "http://hackru.alexvallorosi.com/races",
        method: "GET"
     }).success(function(data) {
         $scope.races = data;
         console.warn(data);
     });

     $scope.submit = function () {
         $http({
             url: "http://hackru.alexvallorosi.com/races",
             method: "POST",
             data: {
                 email: $('#email').val(),
                 vehicle:  $('#vehicle').val(),
                 time: $('#time').val()
             }
          }).success(function(data) {
              console.warn(data);
          });
     }

     $scope.delete = function (index) {
         var current = $scope.races.splice(index, 1);
         console.warn(current);
         console.warn(current[0]._id);
         console.warn(index);
         $http({
             url: "http://hackru.alexvallorosi.com/races/"+current[0]._id,
             method: "DELETE"
          }).success(function(data) {
              console.warn(data);
          });
     }
}

function makeHTTPRequest() {

}