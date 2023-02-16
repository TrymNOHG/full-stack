describe('Contact form', () => {
    beforeEach(() => {
        cy.visit('http://localhost:5173/contact')
    })

    it('Displays two todo items by default', () => {
        cy.get('#name-field').should('have.length', 1)
        cy.get('#email').should('have.length', 1)
        cy.get('#feedback').should('have.length', 1)

    })

    it('Can submit valid Feedback', () => {
        const name = 'Trym'
        const email = 'Trym.Gudvangen@gmail.com'
        const feedback = 'Good job!'


        cy.get('[data-testid=name-field]').last().type(`${name}{enter}`);
        cy.get('[data-testid=email-field]').last().type(`${email}{enter}`);
        cy.get('[data-testid=feedback-field]').last().type(`${feedback}{enter}`);


    })

    it("Successfully posts the feedback", () => {
        cy.intercept('POST', '/feedback').as('Post Status');

        const name = 'Trym'
        const email = 'Trym.Gudvangen@gmail.com'
        const feedback = 'Good job!'


        cy.get('[data-testid=name-field]').last().type(`${name}{enter}`);
        cy.get('[data-testid=email-field]').last().type(`${email}{enter}`);
        cy.get('[data-testid=feedback-field]').last().type(`${feedback}{enter}`);

        cy.wait("@Post Status").then((intercept) => {
            const { statusCode } = intercept.response;

            expect(statusCode).to.eq(201);
        })
    })

    it("Fails to post the feedback with invalid input", () => {
        cy.intercept('POST', '/feedback').as('Post Status');

        const name = 'Trym'
        const email = 'Trym.Gudvangen@'
        const feedback = 'Good job!'


        cy.get('[data-testid=name-field]').last().type(`${name}{enter}`);
        cy.get('[data-testid=email-field]').last().type(`${email}{enter}`);
        cy.get('[data-testid=feedback-field]').last().type(`${feedback}{enter}`);

        //TODO: still not accurate test

    })


})
describe('Test with valid input', () => {
    beforeEach(() => {
        cy.visit('http://localhost:5173/contact')
    })


})
describe('Calculator', () => {
    beforeEach(() => {
        cy.visit('http://localhost:5173/calculator')
    })


})



