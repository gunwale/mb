<!DOCTYPE html>
<html>
    <script type="server/groovy">
        import com.daimler.Car
        car = Car.lookup("1")
    </script>
    <head>
    	<div>
        <title>111111 ${car.brand}</title>
        </div>
    </head>
    <body>
        <h1 title="${car.brand}">${car.brand}</h1>
        <h2 data-if="car.ecoFriendly" title="${car.fuelType}">Fuel Type:
            ${car.fuelType}</h2>
        <div data-loop-model="car.models">Model: ${model}</div>
    </body>
</html>

