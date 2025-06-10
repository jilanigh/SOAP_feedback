# Système de Feedback sur Produits Numériques - Documentation SOAP

Ce projet implémente un service web SOAP pour la gestion des retours d'expérience sur des produits numériques SaaS.

## Table des Matières
1. [Description Générale](#description-générale)
2. [Endpoints SOAP](#endpoints-soap)
3. [Exemples d'Utilisation](#exemples-dutilisation)
4. [Installation et Démarrage](#installation-et-démarrage)
5. [Structure du Projet](#structure-du-projet)

## Description Générale

Le système permet de :
- Gérer les produits numériques
- Collecter les retours d'expérience des utilisateurs
- Analyser les feedbacks
- Gérer les fonctionnalités des produits

## Endpoints SOAP

### 1. Gestion des Produits Numériques

#### 1.1 getProduct
- **Description** : Récupère les détails d'un produit numérique
- **Endpoint** : `/ws/products`
- **Méthode** : `getProduct`
- **Requête** :
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://feedback.com/soap">
   <soapenv:Body>
      <gs:getProductRequest>
         <gs:productId>1</gs:productId>
      </gs:getProductRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
- **Réponse** :
```xml
<SOAP-ENV:Envelope>
   <SOAP-ENV:Body>
      <ns2:getProductResponse>
         <ns2:product>
            <ns2:id>1</ns2:id>
            <ns2:name>CRM Pro</ns2:name>
            <ns2:version>2.1.0</ns2:version>
            <ns2:description>Système de gestion de la relation client</ns2:description>
            <ns2:category>CRM</ns2:category>
            <ns2:releaseDate>2024-01-15</ns2:releaseDate>
            <ns2:status>ACTIVE</ns2:status>
         </ns2:product>
      </ns2:getProductResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

#### 1.2 getAllProducts
- **Description** : Liste tous les produits numériques
- **Endpoint** : `/ws/products`
- **Méthode** : `getAllProducts`
- **Requête** :
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <gs:getAllProductsRequest>
         <gs:category>CRM</gs:category>
         <gs:status>ACTIVE</gs:status>
      </gs:getAllProductsRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 2. Gestion des Feedbacks

#### 2.1 addFeedback
- **Description** : Ajoute un nouveau feedback
- **Endpoint** : `/ws/feedbacks`
- **Méthode** : `addFeedback`
- **Requête** :
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <gs:addFeedbackRequest>
         <gs:productId>1</gs:productId>
         <gs:rating>4</gs:rating>
         <gs:comment>Interface utilisateur intuitive et fonctionnelle</gs:comment>
         <gs:userId>1</gs:userId>
         <gs:feature>Dashboard Analytics</gs:feature>
         <gs:sentiment>POSITIVE</gs:sentiment>
      </gs:addFeedbackRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
- **Réponse** :
```xml
<SOAP-ENV:Envelope>
   <SOAP-ENV:Body>
      <ns2:addFeedbackResponse>
         <ns2:feedback>
            <ns2:id>1</ns2:id>
            <ns2:productId>1</ns2:productId>
            <ns2:rating>4</ns2:rating>
            <ns2:comment>Interface utilisateur intuitive et fonctionnelle</ns2:comment>
            <ns2:date>2024-03-20T10:30:00</ns2:date>
            <ns2:userId>1</ns2:userId>
            <ns2:feature>Dashboard Analytics</ns2:feature>
            <ns2:sentiment>POSITIVE</ns2:sentiment>
            <ns2:status>PENDING</ns2:status>
         </ns2:feedback>
      </ns2:addFeedbackResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

#### 2.2 getProductFeedbacks
- **Description** : Récupère les feedbacks d'un produit
- **Endpoint** : `/ws/feedbacks`
- **Méthode** : `getProductFeedbacks`
- **Requête** :
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <gs:getProductFeedbacksRequest>
         <gs:productId>1</gs:productId>
         <gs:dateRange>
            <gs:startDate>2024-01-01</gs:startDate>
            <gs:endDate>2024-03-20</gs:endDate>
         </gs:dateRange>
         <gs:rating>4</gs:rating>
         <gs:sentiment>POSITIVE</gs:sentiment>
      </gs:getProductFeedbacksRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

#### 2.3 getFeedbackAnalytics
- **Description** : Analyse des feedbacks
- **Endpoint** : `/ws/feedbacks/analytics`
- **Méthode** : `getFeedbackAnalytics`
- **Requête** :
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <gs:getFeedbackAnalyticsRequest>
         <gs:productId>1</gs:productId>
         <gs:timeRange>LAST_30_DAYS</gs:timeRange>
      </gs:getFeedbackAnalyticsRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
- **Réponse** :
```xml
<SOAP-ENV:Envelope>
   <SOAP-ENV:Body>
      <ns2:getFeedbackAnalyticsResponse>
         <ns2:analytics>
            <ns2:averageRating>4.2</ns2:averageRating>
            <ns2:sentimentDistribution>
               <ns2:positive>65</ns2:positive>
               <ns2:neutral>25</ns2:neutral>
               <ns2:negative>10</ns2:negative>
            </ns2:sentimentDistribution>
            <ns2:featureFeedbackCount>
               <ns2:feature name="Dashboard Analytics">45</ns2:feature>
               <ns2:feature name="Reporting">30</ns2:feature>
            </ns2:featureFeedbackCount>
            <ns2:trendAnalysis>
               <ns2:trend period="last30days" direction="UP" percentage="15"/>
            </ns2:trendAnalysis>
         </ns2:analytics>
      </ns2:getFeedbackAnalyticsResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

### 3. Gestion des Utilisateurs

#### 3.1 createUser
- **Description** : Crée un nouvel utilisateur
- **Endpoint** : `/ws/users`
- **Méthode** : `createUser`
- **Requête** :
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <gs:createUserRequest>
         <gs:email>user@company.com</gs:email>
         <gs:company>Company Name</gs:company>
         <gs:role>Developer</gs:role>
         <gs:subscriptionType>PREMIUM</gs:subscriptionType>
      </gs:createUserRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

#### 3.2 getUserFeedbacks
- **Description** : Récupère les feedbacks d'un utilisateur
- **Endpoint** : `/ws/users/feedbacks`
- **Méthode** : `getUserFeedbacks`
- **Requête** :
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <gs:getUserFeedbacksRequest>
         <gs:userId>1</gs:userId>
         <gs:productId>1</gs:productId>
      </gs:getUserFeedbacksRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### 4. Gestion des Fonctionnalités

#### 4.1 getProductFeatures
- **Description** : Liste les fonctionnalités d'un produit
- **Endpoint** : `/ws/features`
- **Méthode** : `getProductFeatures`
- **Requête** :
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <gs:getProductFeaturesRequest>
         <gs:productId>1</gs:productId>
         <gs:status>ACTIVE</gs:status>
      </gs:getProductFeaturesRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

#### 4.2 getFeatureFeedback
- **Description** : Récupère les feedbacks sur une fonctionnalité
- **Endpoint** : `/ws/features/feedback`
- **Méthode** : `getFeatureFeedback`
- **Requête** :
```xml
<soapenv:Envelope>
   <soapenv:Body>
      <gs:getFeatureFeedbackRequest>
         <gs:featureId>1</gs:featureId>
         <gs:dateRange>
            <gs:startDate>2024-01-01</gs:startDate>
            <gs:endDate>2024-03-20</gs:endDate>
         </gs:dateRange>
      </gs:getFeatureFeedbackRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

## Installation et Démarrage

1. Prérequis
   - Java 11 ou supérieur
   - Maven 3.6 ou supérieur

2. Installation
   ```bash
   git clone [URL_DU_REPO]
   cd soap-feedback
   mvn clean install
   ```

3. Démarrage
   ```bash
   mvn spring-boot:run
   ```

4. Accès au WSDL
   ```
   http://localhost:8080/ws/feedback.wsdl
   ```

## Structure du Projet

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── feedback/
│   │           ├── config/         # Configuration Spring
│   │           ├── endpoint/       # Endpoints SOAP
│   │           ├── model/          # Entités JPA
│   │           ├── service/        # Services métier
│   │           └── FeedbackApplication.java
│   └── resources/
│       └── feedback.xsd           # Schéma XSD
└── test/                         # Tests unitaires
```

## Exemples d'Utilisation

### Exemple 1 : Ajouter un Feedback

1. Préparer la requête SOAP
2. Envoyer la requête à l'endpoint `/ws/feedbacks`
3. Traiter la réponse

### Exemple 2 : Obtenir les Analytics

1. Préparer la requête SOAP
2. Envoyer la requête à l'endpoint `/ws/feedbacks/analytics`
3. Analyser les statistiques retournées

## Support

Pour toute question ou assistance :
- Créer une issue sur le repository
- Contacter l'équipe de développement
- Consulter la documentation technique 