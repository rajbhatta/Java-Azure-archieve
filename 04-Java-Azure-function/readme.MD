## How to create Azure function in Java ##
#### 1. Find proper archtype from the internet ####
<img src="arch-step1.png"/>

#### 2. Create maven project but check create from archtype ####
<img src="arch-step2.png"/>

#### 3. Locate azure properties ####
<img src="arch-step3.png"/>

#### 4. Add Azure properties ####
<img src="arch-step4.png"/>

#### 5. Update Azure properties ####
<img src="arch-step5.png"/>


## How to  Azure functions ? ##

### 1. To Deploy to Azure portal ###
- mvn clean install package azure-functions:deploy

### 2. To run locally ###
- mvn package azure-functions:run

### 3. To debug Azure function locally ###
- mvn package azure-functions:run - DenableDebug