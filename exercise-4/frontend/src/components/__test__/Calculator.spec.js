import { createLocalVue, mount} from "@vue/test-utils";
import Calculator from "@/components/Calculator.vue";
import {assert, describe} from 'vitest'

describe('Calculator tests', () => {
    describe('Correct arithmetic', () => {
        test("Multiply function works as expected",()=>{
            expect(Calculator).toBeTruthy();
            let number1 = 5;
            let number2 = 2;
            let expectedVal = 10;

            let actualVal = Calculator.methods.multiply(number1, number2)

            expect(expectedVal).toBe(actualVal)
        })

        test("Divide function works as expected",()=>{
            expect(Calculator).toBeTruthy();
            let number1 = 10;
            let number2 = 2;
            let expectedVal = 5;

            let actualVal = Calculator.methods.divide(number1, number2)

            expect(expectedVal).toBe(actualVal)
        })

        test("Addition function works as expected",()=>{
            expect(Calculator).toBeTruthy();
            let number1 = 5;
            let number2 = 2;
            let expectedVal = 7;

            let actualVal = Calculator.methods.add(number1, number2)

            expect(expectedVal).toBe(actualVal)
        })

        test("Subtract function works as expected",()=>{
            expect(Calculator).toBeTruthy();
            let number1 = 5;
            let number2 = 2;
            let expectedVal = 3;

            let actualVal = Calculator.methods.subtract(number1, number2)

            expect(expectedVal).toBe(actualVal)
        })
    })

    describe('Special Buttons work', () => {
        test('Ans button is null if there is no previous entry', () => {
            let expectedAns = null

            let actualAns = Calculator.data().result

            assert.equal(actualAns, expectedAns)
        })

        test('Ans button is previous entry', () => {
            const wrapper = mount(Calculator)
            let expectedAns = 5

            wrapper.vm.$data.result = 5;

            let actualAns = wrapper.vm.result

            assert.equal(actualAns, expectedAns)
            assert.notEqual(actualAns, 6)

        })

        test('Del button', async () => {
            //Add an input to calculator first
            const wrapper = mount(Calculator)
            wrapper.vm.add_input(5);

            // Test that array contains the input
            let expectedInputArr = [5]
            let actualInputArr = wrapper.vm.input
            expect(expectedInputArr).toStrictEqual(actualInputArr)


            let expectedFinalArr = []

            wrapper.vm.add_input('del')
            let actualFinalArr = wrapper.vm.input

            expect(expectedFinalArr).toStrictEqual(actualFinalArr)

        })

        test('C button', () => {
            const wrapper = mount(Calculator)
            wrapper.vm.$data.input = ['5', '+', '1']
            let expectedArr = []

            wrapper.vm.add_input('c')
            let actualArr = wrapper.vm.input

            expect(expectedArr).toStrictEqual(actualArr)
        })

    })

    describe('Display is correct', () => {
        test('Calculate properly creates string', () => {
            // Add input to the component
            const wrapper = mount(Calculator)
            wrapper.vm.$data.input = ['5', '+', '5']
            let expectedString = '5 + 5 '

            let actualString = wrapper.vm.calculation
            // let actString = wrapper.find('[data-testid="calc-screen"]').text()

            expect(actualString).toStrictEqual(expectedString)


        })
    })

    describe('Entries are pushed to the store', () => {
        test('Not pushed if incomplete', () => {

        })

        test('Properly pushed if valid', () => {
            // // Add input to the component
            // const wrapper = mount(Calculator)
            // wrapper.vm.$data.input = ['5', '+', '5']
            //
            // //Push to the store
            // wrapper.vm.calculate()
            //
            // // let storeEntry = wrapper.vm.


        })
    })

})


