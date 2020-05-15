/// <reference types="Cypress" />

context('Assertions', () => {
    

    describe("Tests", () => {
        beforeEach(() => {
          cy.fixture("users").as("users");
        });
        it("Should be able to login", function() {
            cy.visit('http://localhost:3000/');
          cy
            .get('input[name="username"]')
            .type(this.users.username)
            .should("have.value", this.users.username);
          cy
            .get('input[name="password"]')
            .type(this.users.password)
            .should("have.value", this.users.password);

            cy.get('button').click();
            cy.get('button[name="logout"]');
            
        });

        it("Should count clicks", function() {
          cy.get('button[name = "counter"]').then(($button) => {
              const text = $button.text();
              cy.get('button[name = "counter"]').click();

              cy.get('button[name = "counter"]').should(($button2) => {
                  expect($button2.text()).not.to.eq(text)
              })
          })

        })

        it("Should log out", function() {
            cy.get('button[name="logout"]').click();
            cy.get ('input[name="username"]');
        })

        it("Shouldn't be able to login with wrong details", function() {
            cy
            .get('input[name="username"]')
            .type(this.users.wrongUsername)
            .should("have.value", this.users.wrongUsername);
            cy
            .get('input[name="password"]')
            .type(this.users.wrongPassword)
            .should("have.value", this.users.wrongPassword);
            cy.get("button").click();
          })

          it("Shouldn't be able to login with empty password and username", function() {
            cy.visit('http://localhost:3000/');
          cy
            .get('input[name="username"]')
          cy
            .get('input[name="password"]')    
            cy.get('button').click();
        });

        it("Should be able to register", function() {
            cy.get('div[name="registerTab"]').click();
            cy
            .get('input[name="username"]')
            .type(this.users.wrongUsername)
            .should("have.value", this.users.wrongUsername);
            cy
            .get('input[name="password"]')
            .type(this.users.password)
            .should("have.value", this.users.password);
            cy.get("button").click();
        })
        
      })
})