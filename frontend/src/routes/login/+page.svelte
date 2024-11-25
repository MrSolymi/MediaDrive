<script lang="ts">
    import * as Tabs from '$lib/components/ui/tabs/index.js'
    import * as Card from '$lib/components/ui/card/index.js'
    import { Button } from '$lib/components/ui/button/index.js'
    import { Input } from '$lib/components/ui/input/index.js'
    import { Label } from '$lib/components/ui/label/index.js'
    import Centered from '$lib/Centered.svelte'
    import LoginForm from '$components/auth/LoginForm.svelte'
    import type { PageData } from './$types'

    export let data: PageData
</script>

<Centered>
    <Tabs.Root value="account" class="w-[500px]">
        <Tabs.List class="grid w-full grid-cols-2">
            <Tabs.Trigger value="account">Sign In</Tabs.Trigger>
            <Tabs.Trigger value="reg">Registration</Tabs.Trigger>
        </Tabs.List>
        <Tabs.Content value="account">
            <LoginForm data={data.form}/>
        </Tabs.Content>
        <Tabs.Content value="reg">
            <Card.Root>
                <Card.Header>
                    <Card.Title>Registration</Card.Title>
                    <Card.Description>You need a token to register.</Card.Description>
                </Card.Header>
                <Card.Content class="space-y-2">
                    <div class="space-y-1">
                        <Label for="current">Invite token</Label>
                        <Input id="current" placeholder="your token" />
                    </div>
                </Card.Content>
                <Card.Footer>
                    <Button>Redeem token</Button>
                </Card.Footer>
            </Card.Root>
        </Tabs.Content>
    </Tabs.Root>
</Centered>
