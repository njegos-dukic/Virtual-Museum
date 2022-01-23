export interface VirtualBankRequest {
    firstName: string,
    lastName: string,
    cardType: string,
    cardNumber: string,
    expirationDate: string,
    cvv: string,
    email: string,
    tourId: number,
    userId: number
}