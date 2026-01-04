# Feature: Card Payment - Happy Path

## User Story

As a merchant, I want to accept card payments from customers for their orders, so that I can receive payments and complete sales.

## Acceptance Criteria

- The user can initiate a payment by entering an amount.
- The app can capture card information using a fake POS SDK.
- The app sends the payment request to a fake Payment Gateway.
- The payment is processed successfully (happy path).
- The app receives a successful payment confirmation with a transaction ID.
- The payment intent status is updated to `COMPLETED` in the local database.
- The UI is updated to show the payment success status to the merchant.

## Implementation Tasks

### 1. UI for Payment Flow
- Create a screen to enter the payment amount.
- Create a screen for "reading" the card (simulated by the fake POS SDK).
- Create a screen to display the payment result (success or failure).

### 2. Fake POS SDK
- Create a fake `POSSDK` class that simulates reading a card.
- It should have a method like `readCard()` that returns a fake card token after a short delay.

### 3. Fake Payment Gateway
- Create a fake `PaymentGateway` class that simulates processing a payment.
- It should have a method like `charge(amount, idempotencyKey, cardToken)`.
- For this initial implementation, it should always return a successful response with a unique `transactionId`.
- The gateway should handle the `idempotencyKey` to prevent duplicate charges for the same key.

### 4. Payment Repository
- Implement the `createPaymentIntent` method to generate and save a `PENDING` payment intent to the local database.
- Implement the `startCardPayment` method which:
    - Loads the payment intent from the database.
    - Calls the fake `POSSDK` to get a card token.
    - Calls the fake `PaymentGateway` to charge the payment.
    - Updates the payment intent in the database to `COMPLETED` on success.

### 5. ViewModels and State Management
- Create ViewModels to manage the state of the payment screens.
- Ensure the UI correctly reflects the state of the payment process (e.g., showing a loading indicator during payment processing).

### 6. Local Persistence (Database)
- Define the schema for the `PaymentIntent` entity to be stored in the local database (e.g., using Room).
- The entity should include fields for `intentId`, `amount`, `status`, `idempotencyKey`, `transactionId`, and `lastError`.

### 7. Unit Tests
- Write unit tests for the `PaymentRepository` to verify the happy path flow.
- Write unit tests for the ViewModels to check state transitions.

## Out of Scope

- **Retry Logic:** Handling of retryable failures, timeouts, or network errors. All failures will be treated as final `FAILED` state for now.
- **QR Payments:** This feature only covers card payments.
- **Partial Payments:** The payment must cover the full amount.
- **Real Security:** No real encryption or secure key handling will be implemented in this feature.
- **UI Complexity:** The UI should be simple and functional, without complex design elements.
