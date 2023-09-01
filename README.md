# Description
Image you're working for a company which provides financial services worldwide.
As a core java backend developer, please develop a new service which allows consumers to
calculate the net price from a gross price.

Pseudo signature of the service:
```
netPrice = calculateNetPrice(grossPrice, countryIso);
```

Pseudo examples:
```
81 = calculateNetPrice(100, DE);
1.6 = calculateNetPrice(1.99, FR);
```

Your new service needs access to a taxRateProvider which returns for any given country the
current VAT required to calculate the net price. For the purpose of this assignment, you can
represent the tax data used by the taxRateProvider as a simple map of the country to a tax rate.
E.g.
```
"DE":"0.19", // Germany has 19% VAT on standard taxable goods
"FR":"0.20", // France has 20% VAT on standard taxable goods
```

Please provide a fully runnable (mvn or gradle) and testable java implementation (8 or higher)
along with any required instruction for the reviewer.

## Required tools

1. [Java 17](https://adoptopenjdk.net/)
2. [Docker](https://www.docker.com/products/docker-desktop/)
##### How to build the project?
1. Run `mvn clean install`

##### How to run from docker?
1. Run `docker compose -f docker-compose.yaml up`. The application is accessible from `http://localhost:9100`