import {mount} from "@vue/test-utils";
import Log from "@/components/Log.vue";
import { assertType, expectTypeOf } from 'vitest'
test('my types work properly', () => {
    expectTypeOf(mount).toBeFunction()
    expectTypeOf(mount).parameter(0).toMatchTypeOf<{ name: string }>()

    // @ts-expect-error name is a string
    assertType(mount({ name: 42 }))
})
