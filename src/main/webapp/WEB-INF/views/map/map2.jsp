<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html, body {
	    height: 100%;
	    margin: 0;
	    padding: 0;
	  }
    </style>
  </head>
  <body>
    <div id="map" style="height:100%"></div>
    <script>
    var map;
    var marker;
    var marker2;
    var allData = [];  // csvから読み込んだ緯度経度情報を入れるための2次元配列
    function initMap() {
        /*if (!navigator.geolocation) {
            alert('Geolocation APIに対応していません');
            return false;
        }*/
     
        // 現在地の取得
        // navigator.geolocation.getCurrentPosition(function(position) {
            // 緯度経度の取得
            var latLng = new google.maps.LatLng(35.694162,139.694251);
     
            // 地図の作成
            map = new google.maps.Map(document.getElementById('map'), {
                center: latLng,
                zoom: 16,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                fullscreenControl: false
            });

            var start = new google.maps.LatLng(35.689407,139.700306);
            var location = new google.maps.LatLng(35.694162,139.694251);
            marker = new google.maps.Marker({
              position: location,
              icon: {
                path: google.maps.SymbolPath.CIRCLE,
                scale: 5
              },
              draggable: true,
              map: map
            });
            marker2 = new google.maps.Marker({
              position: start,
              draggable: true,
              map: map
            });
            function loadCSV(targetFile) {
              // 読み込んだデータを1行ずつ格納する配列
              // XMLHttpRequestの用意
              var request = new XMLHttpRequest();
              request.open("get", targetFile, false);
              request.send(null);
              // 読み込んだCSVデータ
              var csvData = request.responseText;
              // CSVの全行を取得
              // var lines = csvData.split("\n");
              var lines = new Array("35.68940,139.700306","35.6898305,139.6995175","35.690261,139.698729","35.6916335,139.6989055","35.693006,139.699082","35.693723,139.698390","35.693451,139.696861","35.6938065,139.695556","35.694162,139.694251");

              for (var i = 0; i < lines.length; i++) {
                // 1行ごとの処理
                var wordSet = lines[i].split(",");
                var wordData = {
                  name: wordSet[0],
                  age: wordSet[1]
                };

                allData.push(wordData);
              }
            }
             
            loadCSV("data.csv");

            function toRender(result){
              directionsDisplay = new google.maps.DirectionsRenderer();
              directionsDisplay.setDirections(result); //取得した情報をset
              directionsDisplay.setMap(map); //マップに描画
            }
        /* }, function() {
            alert('位置情報取得に失敗しました');
        });*/
    }

    var count = 0;
    var positionNow = function(){
      if( marker2 ){ //現在地マーカーが設置されている場合は消去
	    marker2.setMap(null);
      }

      var mapData = { 'x':parseFloat(allData[count].name), 'y':parseFloat(allData[count].age), 'balloon':'現在位置' };
      makeMarker( mapData ); //現在地マーカーを設置

  	  if (count < allData.length - 1){
    	count++;
      }
    }
    setInterval(positionNow,3000);

	//マーカー作成
	function makeMarker( mapData ){
	  marker2 = new google.maps.Marker({
		position : new google.maps.LatLng(mapData.x,mapData.y), 
		map: map
	  });

	  var infoWindow = new google.maps.InfoWindow();
	  google.maps.event.addListener(marker2, 'click', function() {
		if (currentWindow) {
		  currentWindow.close();
		}
		infoWindow.setContent(mapData.balloon);
		infoWindow.open(map,marker2);
		currentWindow = infoWindow;
	  });
	}

    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyArezoDXkDfT2u0p2gqC6W1AYRwObp9Z_E&callback=initMap&libraries=places">
    </script>
    <style>
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
  </body>
</html>