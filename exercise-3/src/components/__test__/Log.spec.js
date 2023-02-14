import { mount } from "@vue/test-utils";
import Log from "@/components/Log.vue";
import Store from '@/store/index.js'
import { assert, assertType, describe, expect, expectTypeOf } from 'vitest'

describe('Log', () => {
    describe('Correct Display', () => {
        test('Displays latest updates to store', async () => {
            // const logWrapper = mount(Log)
            // const storeWrapper = mount(Store)
            // let expectedString = '5 + 5 = 10'
            //
            // storeWrapper.vm.$data.state.logs.logEntries = [expectedString]
            //
            // let actualString = logWrapper.vm.updateEntries
            //
            // expect(actualString).toStrictEqual(actualString)

        })
    })

})


