<script lang="ts">
    import * as Form from '@/ui/form'
    import { superForm, type Infer, type SuperValidated } from 'sveltekit-superforms'
    import { zodClient } from 'sveltekit-superforms/adapters'

    import * as Card from '$lib/components/ui/card/index.js'
    import { Input } from '$lib/components/ui/input/index.js'
    import { loginSchema, type LoginSchema } from '$lib/schema'

    export let data: SuperValidated<Infer<LoginSchema>>

    const form = superForm(data, {
        validators: zodClient(loginSchema)
    })

    const { form: formData, enhance } = form
</script>

<form method="POST" use:enhance>
    <Card.Root>
        <Card.Header>
            <Card.Title>Account Sign In</Card.Title>
            <Card.Description>Sign in with username and password.</Card.Description>
        </Card.Header>
        <Card.Content class="space-y-2">
            <Form.Field class="space-y-1" {form} name="username">
                <Form.Control let:attrs>
                    <Form.Label>Username</Form.Label>
                    <Input placeholder="username" {...attrs} bind:value={$formData.username} />
                </Form.Control>
                <Form.FieldErrors />
            </Form.Field>
            <Form.Field class="space-y-1" {form} name="password">
                <Form.Control let:attrs>
                    <Form.Label>Password</Form.Label>
                    <Input
                        placeholder="password"
                        {...attrs}
                        bind:value={$formData.password}
                        type="password"
                    />
                </Form.Control>
                <Form.FieldErrors />
            </Form.Field>
        </Card.Content>
        <Card.Footer>
            <Form.Button>Sign in</Form.Button>
        </Card.Footer>
    </Card.Root>
</form>
